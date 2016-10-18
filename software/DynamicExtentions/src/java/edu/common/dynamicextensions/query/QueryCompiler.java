package edu.common.dynamicextensions.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import edu.common.dynamicextensions.domain.nui.Container;
import edu.common.dynamicextensions.domain.nui.Control;
import edu.common.dynamicextensions.domain.nui.LookupControl;
import edu.common.dynamicextensions.domain.nui.MultiSelectControl;
import edu.common.dynamicextensions.domain.nui.SubFormControl;
import edu.common.dynamicextensions.napi.VersionedContainer;
import edu.common.dynamicextensions.napi.impl.VersionedContainerImpl;
import edu.common.dynamicextensions.query.ast.*;
import edu.common.dynamicextensions.query.ast.FilterExpressionNode.Op;

public class QueryCompiler
{
	private static final Logger logger = Logger.getLogger(QueryCompiler.class);

    private int tabCnt;
    
    private String rootFormName;
    
    private String query;
    
    private String restriction;
    
    private QueryExpressionNode queryExpr;
    
    private JoinTree queryJoinTree;
    
    private int numQueries;
    
    private boolean vcEnabled;
    
    public QueryCompiler(String rootFormName, String query) {
        this(rootFormName, query, null);
    }
    
    public QueryCompiler(String rootFormName, String query, String restriction) {
        this.rootFormName = rootFormName;
        this.query = query;
        this.restriction = restriction;
    }
    
    public QueryCompiler enabledVersionedForms(boolean vcEnabled) {
    	this.vcEnabled = vcEnabled;
    	return this;
    }
    
    public void compile() {
		try {
			QueryParser queryParser = new QueryParser(query);
			queryExpr = queryParser.getQueryAst();
			addRestrictions();
			queryJoinTree = buildJoinTree(queryExpr);
		} catch (Exception e) {
			logger.info("Error compiling query: " + query, e);
			throw e;
		}
    }

    public QueryExpressionNode getQueryExpr() {
        return queryExpr;
    }

    public JoinTree getQueryJoinTree() {
        return queryJoinTree;
    }
    
    private void addRestrictions() {
        if (restriction == null) {
            return;
        }
        
        FilterExpressionNode newFilter = FilterExpressionNode.andExpr(
                FilterExpressionNode.parenExpr(queryExpr.getFilterExpr()), 
                new QueryParser(restriction).getQueryAst().getFilterExpr());
        queryExpr.setFilterExpr(newFilter);
    }

    private JoinTree buildJoinTree(QueryExpressionNode queryExpr) {
        Map<String, JoinTree> joinMap = analyzeExpr(queryExpr);
        JoinTree rootTree = joinMap.get("0." + rootFormName);
        
        if (rootTree == null) {
            Container rootForm = getContainer(rootFormName);
            rootTree = new JoinTree(rootForm, "t" + tabCnt++);
        }

        List<String> lookupNames = new ArrayList<String>(joinMap.keySet());
        Collections.sort(lookupNames, new Comparator<String>() {
        	public int compare(String arg0, String arg1) {
        		Integer query0 = Integer.parseInt(arg0.split("\\.")[0]);
        		Integer query1 = Integer.parseInt(arg1.split("\\.")[0]);
        		return query0.compareTo(query1);
        	}
        });
        
        for (String formLookupName : lookupNames) {
        	if (formLookupName.equals("0." + rootFormName)) {
                continue;
            }
                     
            JoinTree childTree = joinMap.get(formLookupName);
            
            String dest = formLookupName.substring(formLookupName.indexOf(".") + 1); 
            Path path = PathConfig.getInstance().getPath(rootFormName, dest);
            if (path == null) {
                throw new RuntimeException("No path between root form " + rootFormName + " and " + dest);
            }
            
            int queryId = Integer.parseInt(formLookupName.split("\\.")[0]);
            createPath(queryId, rootTree, childTree, path);
        }

        return rootTree;
    }
    
    private void createPath(int queryId, JoinTree from, JoinTree to, Path path) {
        JoinTree current = createPath(queryId, from, path);
    	//JoinTree current = from;
        
        // direct named path i.e. start field specified and no links
        // added in nextgen
        if (path.getLinks() == null || path.getLinks().isEmpty()) { 
        	JoinTree parentTree = current.getParent();
        	parentTree.removeChild(current);
        	to.setParent(parentTree);
        	to.setForeignKey(current.getForeignKey());
        	to.setParentKey(current.getParentKey());
        	to.addChildrenOf(current);
        	parentTree.addChild(queryId + "." + current.getFormName(), to);        	        	
        	return;
        }
        
        for (PathLink link : path.getLinks()) {
            if (link.getRefTab() == null && to.getParent() == null) {            	
                to.setParent(current);
                to.setForeignKey(link.getRefTabKey());
                to.setParentKey(link.getKey());
                //current.addChild(to.getTab(), to); // TODO: PAND Fix
                current.addChild(to.getAlias(), to);
                break;
            }
            
            JoinTree child = current.getChild(link.getRefTab());            
            if (child == null) {
                child = new JoinTree(link.getRefTab(), "t" + tabCnt++);
                child.setParent(current);
                child.setForeignKey(link.getRefTabKey());
                child.setParentKey(link.getKey());
                current.addChild(child.getAlias(), child); // Earlier: name = child.getTab()                
            }
            current = child;
        }
        
        current.setInnerJoin(path.isWildCard());
    }

    private JoinTree createPath(int queryId, JoinTree formTree, Path path) {
    	String startField = path.getStartField();
    	
        if (startField == null) {
            return formTree;
        }
        
        String fieldNameParts[] = startField.split("\\.");
        for (int i = 0; i < fieldNameParts.length; i++) {
            JoinTree child = formTree.getChild(queryId + "." + fieldNameParts[i]);
            if (child == null && i != (fieldNameParts.length  - 1)) { // TODO: should this be done?
            	child = formTree.getChild("0." + fieldNameParts[i]);
            }            	

            if (child == null) {
            	SubFormControl sfCtrl = (SubFormControl)formTree.getForm().getControlByUdn(fieldNameParts[i]);
                JoinTree sfTree = getSubFormTree(formTree, sfCtrl);
                sfTree.setSubForm(false); // TODO: This is quick fix. Need to be thought out
                formTree.addChild(queryId + "." + fieldNameParts[i], sfTree); // PAND fix "0."
                formTree = sfTree;
            } else {
                formTree = child;
            }
        }

        return formTree;
    }

    private JoinTree getSubFormTree(JoinTree parentNode, SubFormControl sfCtrl) {
        JoinTree sfTree = new JoinTree();
        sfTree.setForm(sfCtrl.getSubContainer());
        sfTree.setTab(sfCtrl.getSubContainer().getDbTableName());
        sfTree.setParent(parentNode);
        sfTree.setAlias((new StringBuilder()).append("t").append(tabCnt++).toString());
        sfTree.setParentKey(sfCtrl.getParentKey());
        sfTree.setForeignKey(sfCtrl.getForeignKey());
        sfTree.setSubForm(true);
        sfTree.setExtnFk(sfCtrl.getExtnFkColumn());
        sfTree.setFormIdCol(sfCtrl.getFormIdColumn());
        return sfTree;
    }
    
    private JoinTree getFieldTree(JoinTree parentNode, Control field) {
		JoinTree fieldTree = new JoinTree();
		fieldTree.setField(field);
		fieldTree.setParent(parentNode);
		fieldTree.setAlias((new StringBuilder()).append("t").append(tabCnt++).toString());

		if (field instanceof MultiSelectControl) {
			MultiSelectControl msCtrl = (MultiSelectControl) field;
			fieldTree.setTab(msCtrl.getTableName());
			fieldTree.setParentKey(msCtrl.getParentKey());
			fieldTree.setForeignKey(msCtrl.getForeignKey());
		} else if (field instanceof LookupControl) {
			LookupControl luCtrl = (LookupControl) field;
			fieldTree.setTab(luCtrl.getTableName());
			fieldTree.setParentKey(luCtrl.getParentKey());
			fieldTree.setForeignKey(luCtrl.getLookupKey());
		} else {
			throw new RuntimeException("Cannot create field tree for unknown type: " + field.getClass());
		}

		return fieldTree;
    }
        
    private Map<String, JoinTree> analyzeExpr(QueryExpressionNode expr) {
        Map<String, JoinTree> joinMap = new HashMap<>();

        analyzeFilterNodeMarker(0, expr.getFilterExpr(), joinMap);
        expr.setSelectList(analyzeSelectList(expr.getSelectList(), joinMap));

        if (expr.getOrderExpr() != null) {
            expr.setOrderExpr(analyzeOrderExpr(expr.getOrderExpr(), joinMap));
        }

        return joinMap;
    }
    
    private void analyzeFilterNodeMarker(int queryId, FilterNodeMarker expr, Map<String, JoinTree> joinMap) {
        if (expr instanceof FilterNode) {
            FilterNode filter = (FilterNode)expr;
            analyzeFilterNode(queryId, filter, joinMap);
        } else {
            FilterExpressionNode subExpr = (FilterExpressionNode)expr;
            
            if (subExpr.getOperator() == Op.NTHCHILD) {
            	analyzeNthChildFilterNodeMarker(queryId, subExpr, joinMap);
            } else {
            	analyzeFilterExprNode(queryId, subExpr, joinMap);
            }
            
        }
    }
    
    private void analyzeFilterExprNode(int queryId, FilterExpressionNode filterExpr, Map<String, JoinTree> joinMap) {
        for (FilterNodeMarker childExpr : filterExpr.getOperands()) {
            if (filterExpr.getOperator() == Op.PAND) {
                queryId++;
                this.numQueries++;
            } 
            
            analyzeFilterNodeMarker(queryId, childExpr, joinMap);                
        }                	
    }
    
    private void analyzeNthChildFilterNodeMarker(int queryId, FilterExpressionNode expr, Map<String, JoinTree> joinMap) {
    	this.numQueries++;
    	
    	analyzeFilterExprNode(queryId + 1, expr, joinMap);
    	
    	String[] formNames = getFormNames(expr);
    	if (formNames.length != 1) {
    		throw new IllegalArgumentException("nth-child refers to more than one-form");
    	}
       	
        JoinTree childTree = joinMap.remove((queryId + 1) + "." + formNames[0]);
        String treeTable = childTree.getForm().getHierarchyTable();
        String ancestorCol = childTree.getForm().getHierarchyAncestorCol();
        String descendentCol = childTree.getForm().getHierarchyDescendentCol();
        
        if (treeTable == null || ancestorCol == null || descendentCol == null) {
        	throw new IllegalArgumentException("nth-child form do not have relation hierarchy table defined");
        }
        		
        JoinTree parentTree = joinMap.get(queryId + "." + formNames[0]);
        if (parentTree == null) {
        	Container form = getContainer(formNames[0]);
        	parentTree = new JoinTree(form, "t" + tabCnt++);
        	joinMap.put(queryId + "." + formNames[0], parentTree);                		
        }

        JoinTree relationLink = new JoinTree(treeTable, "t" + tabCnt++);
        relationLink.setForeignKey(ancestorCol);
        relationLink.setParentKey(parentTree.getForm().getPrimaryKey());
        relationLink.setParent(parentTree);
        
        childTree.setForeignKey(childTree.getForm().getPrimaryKey());
        childTree.setParentKey(descendentCol);
        childTree.setParent(relationLink);
        
        relationLink.addChild((queryId + 1) + "." + formNames[0], childTree);        
        parentTree.addChild((queryId + 1) + ".hierarchy", relationLink);
    }
    
    private void analyzeFilterNode(int queryId, FilterNode filter, Map<String, JoinTree> joinMap) {
        analyzeExpressionNode(queryId, filter.getLhs(), joinMap);
        analyzeExpressionNode(queryId, filter.getRhs(), joinMap);       
    }
    
    private void analyzeArithExpressionNode(int queryId, ArithExpressionNode expr, Map<String, JoinTree> joinMap) {
        analyzeExpressionNode(queryId, expr.getLeftOperand(), joinMap);
        analyzeExpressionNode(queryId, expr.getRightOperand(), joinMap);        
    }
    
    private void analyzeDateDiffFuncNode(int queryId, DateDiffFuncNode dateDiff, Map<String, JoinTree> joinMap) {
        analyzeExpressionNode(queryId, dateDiff.getLeftOperand(), joinMap);
        analyzeExpressionNode(queryId, dateDiff.getRightOperand(), joinMap);
    }

    private void analyzeDateRangeFuncNode(int queryId, DateRangeFuncNode dateRange, Map<String, JoinTree> joinMap) {
        analyzeExpressionNode(queryId, dateRange.getDateExpr(), joinMap);
    }

	private void analyzeBetweenNode(int queryId, BetweenNode between, Map<String, JoinTree> joinMap) {
		analyzeExpressionNode(queryId, between.getLhs(), joinMap);
		analyzeExpressionNode(queryId, between.getMinNode(), joinMap);
		analyzeExpressionNode(queryId, between.getMaxNode(), joinMap);
	}
    
    private void analyzeExpressionNode(int queryId, ExpressionNode exprNode, Map<String, JoinTree> joinMap) {
        if (exprNode instanceof FieldNode) {
            analyzeField(queryId, (FieldNode)exprNode, joinMap);
        } else if (exprNode instanceof ArithExpressionNode) {
            analyzeArithExpressionNode(queryId, (ArithExpressionNode)exprNode, joinMap);
        } else if (exprNode instanceof DateDiffFuncNode) {
            analyzeDateDiffFuncNode(queryId, (DateDiffFuncNode) exprNode, joinMap);
        } else if (exprNode instanceof DateRangeFuncNode) {
            analyzeDateRangeFuncNode(queryId, (DateRangeFuncNode) exprNode, joinMap);
        } else if (exprNode instanceof BetweenNode) {
			analyzeBetweenNode(queryId, (BetweenNode)exprNode, joinMap);
		} else if (exprNode instanceof RoundOffNode) {
			analyzeExpressionNode(queryId, ((RoundOffNode)exprNode).getExprNode(), joinMap);
		} else if (exprNode instanceof AggregateNode) {
			analyzeField(queryId, ((AggregateNode)exprNode).getField(), joinMap);
		} else if (exprNode instanceof ConcatNode) {
            ConcatNode concatNode = (ConcatNode)exprNode;
            for (ExpressionNode arg : concatNode.getArgs()) {
                analyzeExpressionNode(queryId, arg, joinMap);
            }
        }
    }
    
    private SelectListNode analyzeSelectList(SelectListNode selectList, Map<String, JoinTree> joinMap) {
        SelectListNode finalSelectList = new SelectListNode();
        finalSelectList.setDistinct(selectList.isDistinct());
        finalSelectList.getElements().addAll(analyzeProjOrderExprList(selectList.getElements(), joinMap));
        return finalSelectList;
    }

    private OrderExprListNode analyzeOrderExpr(OrderExprListNode nodeList, Map<String, JoinTree> joinMap) {
        List<OrderExprNode> finalExprs = new ArrayList<>();

        for (OrderExprNode exprNode : nodeList.getExprs()) {
            for (ExpressionNode expr : analyzeProjOrderExprList(Collections.singletonList(exprNode.getExpr()), joinMap)) {
                OrderExprNode finalExpr = new OrderExprNode();
                finalExpr.setDescending(exprNode.isDescending());
                finalExpr.setExpr(expr);
                finalExprs.add(finalExpr);
            }
        }

        OrderExprListNode finalExprList = new OrderExprListNode();
        finalExprList.setExprs(finalExprs);
        return finalExprList;
    }

    private List<ExpressionNode> analyzeProjOrderExprList(List<ExpressionNode> exprs, Map<String, JoinTree> joinMap) {
        List<Set<ExpressionNode>> clonedExprsList = new ArrayList<>();

        for (int i = 0; i < exprs.size(); ++i) {
            clonedExprsList.add(new LinkedHashSet<>());
        }

        for (int exprIdx = 0; exprIdx < exprs.size(); ++exprIdx) {
            for (int i = 0; i <= numQueries; ++i) {
                ExpressionNode selectOrderNode = exprs.get(exprIdx).copy();
                if (analyzeSelectExpressionNode(i, selectOrderNode, joinMap, true)) { // TODO: Fix area
                    clonedExprsList.get(exprIdx).add(selectOrderNode);
                }
            }
        }

        for (int exprIdx = 0; exprIdx < exprs.size(); ++exprIdx) {
            if (clonedExprsList.get(exprIdx).isEmpty()) {
                ExpressionNode selectOrderNode = exprs.get(exprIdx).copy();
                analyzeSelectExpressionNode(0, selectOrderNode, joinMap, false);
                clonedExprsList.get(exprIdx).add(selectOrderNode);
            }
        }

        List<ExpressionNode> result = new ArrayList<>();
        boolean endOfNodes = false;
        while (!endOfNodes) {
            endOfNodes = true;
            
            for (Set<ExpressionNode> exprNodes : clonedExprsList) {
                if (exprNodes.isEmpty()) {
                    continue;
                }

                Iterator<ExpressionNode> iter = exprNodes.iterator();
                ExpressionNode selectOrderNode = iter.next();
                iter.remove();
                result.add(selectOrderNode);
                endOfNodes = false;
            }           
        }
        
        return result;
    }

    private boolean analyzeSelectArithExpressionNode(int queryId, ArithExpressionNode expr, Map<String, JoinTree> joinMap, boolean failIfAbsent) {
        boolean result = analyzeSelectExpressionNode(queryId, expr.getLeftOperand(), joinMap, failIfAbsent);
        if (result || !failIfAbsent) {
            result = analyzeSelectExpressionNode(queryId, expr.getRightOperand(), joinMap, failIfAbsent); 
        }
        
        return (result || !failIfAbsent);               
    }

    private boolean analyzeSelectConcatExpressionNode(int queryId, ConcatNode concatNode, Map<String, JoinTree> joinMap, boolean failIfAbsent) {
        for (ExpressionNode arg : concatNode.getArgs()) {
            boolean result = analyzeSelectExpressionNode(queryId, arg, joinMap, failIfAbsent);
            if (!result && failIfAbsent) {
                return false;
            }
        }

        return true;
    }
    
    private boolean analyzeSelectDateDiffFuncNode(int queryId, DateDiffFuncNode dateDiff, Map<String, JoinTree> joinMap, boolean failIfAbsent) {
        boolean result = analyzeSelectExpressionNode(queryId, dateDiff.getLeftOperand(), joinMap, failIfAbsent);
        if (result || !failIfAbsent) {
            result = analyzeSelectExpressionNode(queryId, dateDiff.getRightOperand(), joinMap, failIfAbsent);
        }
        
        return (result || !failIfAbsent);
    }

    private boolean analyzeSelectExpressionNode(int queryId, ExpressionNode exprNode, Map<String, JoinTree> joinMap, boolean failIfAbsent) {
        if (exprNode instanceof FieldNode) {
            return analyzeField(queryId, (FieldNode)exprNode, joinMap, failIfAbsent);
        } else if (exprNode instanceof ArithExpressionNode) {
            return analyzeSelectArithExpressionNode(queryId, (ArithExpressionNode)exprNode, joinMap, failIfAbsent);
        } else if (exprNode instanceof DateDiffFuncNode) {
            return analyzeSelectDateDiffFuncNode(queryId, (DateDiffFuncNode)exprNode, joinMap, failIfAbsent);
        } else if (exprNode instanceof AggregateNode) {
        	return analyzeField(queryId, ((AggregateNode)exprNode).getField(), joinMap, failIfAbsent);
        } else if (exprNode instanceof RoundOffNode) {
            return analyzeSelectExpressionNode(queryId, ((RoundOffNode) exprNode).getExprNode(), joinMap, failIfAbsent);
        } else if (exprNode instanceof ConcatNode) {
            return analyzeSelectConcatExpressionNode(queryId, (ConcatNode)exprNode, joinMap, failIfAbsent);
        } else {
            return !failIfAbsent; // literal nodes
        }
    }
    
    private boolean analyzeField(int queryId, FieldNode field, Map<String, JoinTree> joinMap) {
        return analyzeField(queryId, field, joinMap, false);
    }
    
    private boolean analyzeField(int queryId, FieldNode field, Map<String, JoinTree> joinMap, boolean failIfAbsent) {
        String[] fieldNameParts = field.getName().split("\\.");
        String[] captions = new String[fieldNameParts.length];
        
        String formName = fieldNameParts[0];
        if (formName.equals(rootFormName)) {
            queryId = 0;
        }

    	String formLookupName = queryId + "." + formName;
    	JoinTree formTree = joinMap.get(formLookupName);
    	if (formTree == null) {
    		for (Map.Entry<String, JoinTree> jt : joinMap.entrySet()) {
    			if (!jt.getKey().endsWith("." + formName)) {
    				continue;
    			}
    			
    			JoinTree relLink = jt.getValue().getChild(queryId + ".hierarchy");
    			if (relLink != null) {
    				formTree = relLink.getChild(queryId + "." + formName);
    				break;
    			}
    		}
    	}
    	
    	if (formTree == null && failIfAbsent) {
    		return false;
    	}
    	
    	Container form = null;
    	if (formTree == null) {
    		form = getContainer(formName);
    		if (form == null) {
    			throw new IllegalArgumentException("Invalid form in field: " + field.getName());
    		}
    		
    		formTree = new JoinTree(form, "t" + tabCnt++);
    		joinMap.put(formLookupName, formTree);
    	} else {
    		form = formTree.getForm();
    	}    	                
        captions[0] = form.getCaption();
        
        Control ctrl = form.getControlByUdn(fieldNameParts[1]);
        if (ctrl == null) {
        	throw new IllegalArgumentException("Field doesn't exists: " + field.getName());
        }
        
        if (!fieldNameParts[1].equals("extensions") && !fieldNameParts[1].equals("customFields") && (ctrl instanceof SubFormControl) && fieldNameParts.length > 2) {
        	formTree = analyzeSubFormFields(queryId, formTree, fieldNameParts, 1, captions, false /*failIfAbsent*/);
        } else if ((fieldNameParts[1].equals("extensions") || fieldNameParts[1].equals("customFields")) && (ctrl instanceof SubFormControl) && fieldNameParts.length > 3) {
        	formTree = analyzeExtensionFields(queryId, joinMap, formTree, fieldNameParts, captions, false /*failIfAbsent*/);
        } 
        
        if (formTree == null && failIfAbsent) {
        	return false;
        }
        
        if (formTree == null) {
        	throw new IllegalArgumentException("Invalid field: " + field.getName());
        }
        
        form = formTree.getForm();
        ctrl = form.getControlByUdn(fieldNameParts[fieldNameParts.length - 1]);
        if (ctrl == null) {
        	throw new IllegalArgumentException("Invalid field: " + field.getName());
        }
        
        String tabAlias = formTree.getAlias();
        if (ctrl instanceof MultiSelectControl || ctrl instanceof LookupControl) {
        	JoinTree fieldTree = formTree.getChild(queryId + "." + ctrl.getName());
        	if (fieldTree == null && failIfAbsent) {
        		return false;
        	}
        	
        	if (fieldTree == null) {
        		fieldTree = getFieldTree(formTree, ctrl);
        		formTree.addChild(queryId + "." + ctrl.getName(), fieldTree);
        	}
        	
        	tabAlias = fieldTree.getAlias();
        }
        
        captions[captions.length - 1] = ctrl.getCaption();
        field.setCtrl(ctrl);
        field.setTabAlias(tabAlias);
        field.setNodeCaptions(captions);
        return true;
    }    
        
    private JoinTree getSubFormTree(int queryId, JoinTree formTree, String fieldName, boolean failIfAbsent) {
    	return getSubFormTree(queryId, formTree, fieldName, failIfAbsent, null);
    }
    
    private JoinTree getSubFormTree(int queryId, JoinTree formTree, String fieldName, boolean failIfAbsent, String extnForm) {
    	Container form = formTree.getForm();
    	Control ctrl = form.getControlByUdn(fieldName);
    	
    	if (!(ctrl instanceof SubFormControl)) {
    		throw new IllegalArgumentException("Field is not sub-form:" + fieldName);
    	}
    	
    	String key = fieldName;
    	if (extnForm != null) {
    		key += "." + extnForm;
    	}
    	
    	SubFormControl sfCtrl = (SubFormControl)ctrl;
    	JoinTree sfTree = formTree.getChild(queryId + "." + key);
    	if (sfTree == null && failIfAbsent) {
    		return null;
    	}
    	
    	if (sfTree == null) {
    		sfTree = getSubFormTree(formTree, sfCtrl);
    		formTree.addChild(queryId + "." + key, sfTree);
    	}
    	
    	return sfTree; // add join inner when extnForm is not null
    }
        
    private JoinTree analyzeSubFormFields(
    		int queryId, 
    		JoinTree formTree, 
    		String[] fieldNameParts, int startIdx, String[] captions, 
    		boolean failIfAbsent) {
    	
    	JoinTree sfTree = formTree;
    	for (int i = startIdx; i < fieldNameParts.length - 1; i++) {
    		//sfTree = getSubFormTree(queryId, formTree, fieldNameParts[i], failIfAbsent);
    		sfTree = getSubFormTree(
    				queryId, formTree, fieldNameParts[i], 
    				!failIfAbsent ? false : i == startIdx ? failIfAbsent : false);
    		if (sfTree == null) {
    			return null;
    		}
    		
    		formTree = sfTree;
    		captions[i] = sfTree.getForm().getCaption();
    	}
    	
    	return sfTree;
    }
    
    private JoinTree analyzeExtensionFields(
    		int queryId, 
    		Map<String, JoinTree> joinMap, JoinTree formTree, 
    		String[] fieldNameParts, String[] captions, 
    		boolean failIfAbsent) {
    	
    	JoinTree extensionTree = getSubFormTree(queryId, formTree, fieldNameParts[1], failIfAbsent, fieldNameParts[2]);
    	if (extensionTree == null) {
    		return null;    		
    	}
    																																										
    	JoinTree extensionFormTree = extensionTree.getChild(queryId + "." + fieldNameParts[2]);
    	if (extensionFormTree == null && failIfAbsent) {
    		return null;
    	}
    	
    	if (extensionFormTree == null) {
    		Container extensionForm = getContainer(fieldNameParts[2]);
    		if (extensionForm == null) {
    			throw new IllegalArgumentException("Invalid extension form name: " + fieldNameParts[2]);
    		}
    		
    		extensionFormTree = new JoinTree(extensionForm, "t" + tabCnt++);    		
    		extensionFormTree.setParent(extensionTree);
    		extensionFormTree.setExtensionForm(true);
    		
    		extensionTree.addChild(queryId + "." + fieldNameParts[2], extensionFormTree);    		
    	}
    	
    	captions[1] = "$$_" + extensionTree.getForm().getCaption() + "_$$"; 
    	captions[2] = extensionFormTree.getForm().getCaption();
    	return analyzeSubFormFields(queryId, extensionFormTree, fieldNameParts, 3, captions, failIfAbsent);
    }
       
    private Container getContainer(String name) {
    	Container container = null;
    	
    	if (vcEnabled) {
    		// When VC enabled, name refers to the versioned form name 
    		// i.e. form name field of dyextn_forms table
    		VersionedContainer vc = new VersionedContainerImpl();
    		container = vc.getContainer(name);
    	} else {
    		// otherwise, name refers to container name
    		// i.e. name field of dyextn_containers
    		container = Container.getContainer(name);
    	}
    	
    	return container;
    }
    
    private String[] getFormNames(FilterNodeMarker filterMarker) {
    	Set<String> formNames = new HashSet<String>();
    	
    	if (filterMarker instanceof FilterNode) {
    		FilterNode node = (FilterNode)filterMarker;
    		formNames.addAll(Arrays.asList(node.getLhs().getFormNames()));
    		if (node.getRhs() != null) {
    			formNames.addAll(Arrays.asList(node.getRhs().getFormNames()));
    		}    		
    	} else {
    		FilterExpressionNode filterExpr = (FilterExpressionNode)filterMarker;
    		
    		for (FilterNodeMarker subExpr : filterExpr.getOperands()) {
    			formNames.addAll(Arrays.asList(getFormNames(subExpr)));
    		}    		
    	}
    	
    	return formNames.toArray(new String[0]);    	
    }    
}
