package edu.common.dynamicextensions.query;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.common.dynamicextensions.domain.nui.DataType;
import edu.common.dynamicextensions.ndao.DbSettingsFactory;
import edu.common.dynamicextensions.nutility.Util;
import edu.common.dynamicextensions.query.ast.AggregateNode;
import edu.common.dynamicextensions.query.ast.ExpressionNode;
import edu.common.dynamicextensions.query.ast.FieldNode;

public class QueryResultData {
    private ResultColumnLabelFormatter formatter = new DefaultResultColLabelFormatter("# ");
    
    private List<ResultColumn> resultColumns = null;

	private List<ResultColumn> screenedColumns = null;
    
    private List<Object[]> rows = null;
    
    private ShallowWideRowGenerator rowGen = null;
            
    private SimpleDateFormat sdf = null;

    private SimpleDateFormat tsf = null;
    
    private QueryResultScreener screener = null;
    
    private int dbRowsCount;

	public QueryResultData(List<ResultColumn> resultColumns) {
		this.resultColumns = resultColumns;
	}

    public QueryResultData(List<ResultColumn> resultColumns, String dateFormat, String timeFormat) {
		this(resultColumns);

        if (dateFormat != null) {
			sdf = new SimpleDateFormat(dateFormat);
			if (timeFormat != null) {
				tsf = new SimpleDateFormat(dateFormat + " " + timeFormat);
			}
        }
    }

    public QueryResultScreener getScreener() {
        return screener;
    }

    public void setScreener(QueryResultScreener screener) {
        this.screener = screener;
    }

    public void setColumnLabelFormatter(ResultColumnLabelFormatter formatter) {
    	this.formatter = formatter;
    }

    public Integer[] getColumnIndices(String name) {
    	List<Integer> indices = new ArrayList<Integer>();
    	
    	int i = 0;
    	for (ResultColumn column : getResultColumns()) {
    		if (column.getExpression() instanceof FieldNode) {
    			FieldNode node = (FieldNode)column.getExpression();
    			if (node.getName().equals(name)) {
    				indices.add(i);
    			}    			
    		}
    		
    		++i;
    	}
    	
    	return indices.toArray(new Integer[0]);
    }
    
    public String[] getColumnLabels() {
        List<ResultColumn> screenedCols = getResultColumns();
        String[] labels = new String[screenedCols.size()];
        int i = 0;
        for (ResultColumn column : screenedCols) {
        	labels[i++] = column.getColumnLabel(formatter);
        }
        
        return labels;
    }

	public String[] getColumnUrls() {
		List<ResultColumn> screenedCols = getResultColumns();
		String[] urls = new String[screenedCols.size()];
		int i = 0;
		for (ResultColumn column : screenedCols) {
			urls[i++] = column.getUrl();
		}

		return urls;
	}

	public String[] getColumnTypes() {
		List<ResultColumn> screenedCols = getResultColumns();
		String[] types = new String[screenedCols.size()];
		int i = 0;
		for (ResultColumn column : screenedCols) {
			if (column.getExpression() != null && column.getExpression().getType() != null) {
				types[i] = column.getExpression().getType().name();
			}

			++i;
		}

		return types;
	}
    
    public List<ResultColumn> getResultColumns() {
		if (screenedColumns != null) {
			return screenedColumns;
		}

		screenedColumns = resultColumns;
		if (screener != null) {
			screenedColumns = screener.getScreenedResultColumns(resultColumns);
		}

		return screenedColumns;
    }
    
    public void dataSource(List<Object[]> rows) {
    	this.rows = rows;
    }
       
    public void dataSource(ResultSet rs) {
    	List<Object[]> rows = new ArrayList<Object[]>();

		Map<Integer, BigDecimal> cumulative = new HashMap<>();
    	try {
        	int columnCount = rs.getMetaData().getColumnCount();
        	if (columnCount == resultColumns.size() + 1 && DbSettingsFactory.isOracle()) {
        		columnCount--;
        	}
        	
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 0; i < columnCount; ++i) {
					ExpressionNode expr = resultColumns.get(i).getExpression();
                    row[i] = rs.getObject(i + 1);
                    if (row[i] instanceof Date) {
						row[i] = rs.getTimestamp(i + 1);
                    } else if (expr instanceof AggregateNode && ((AggregateNode) expr).isCumulative()) {
						BigDecimal existing = cumulative.get(i);
						if (existing == null) {
							existing = BigDecimal.ZERO;
						}

						if (row[i] != null) {
							existing = existing.add(new BigDecimal(row[i].toString()));
						}

						row[i] = existing;
						cumulative.put(i, existing);
					}
                }
                
                if (screener != null) {
                	row = screener.getScreenedRowData(resultColumns, row);
                }
                
                rows.add(row);
            }    		
    	} catch (Exception e) {
    		throw new RuntimeException("Error traversing result set", e);
    	} finally {
    		cumulative.clear();
			cumulative = null;
		}

    	this.rows = rows;
    	this.dbRowsCount = this.rows.size();
    }
    
    public void dataSource(ShallowWideRowGenerator rowGen) {
    	this.rowGen = rowGen;
    }

    public List<Object[]> getRows() {
		Iterator<Object[]> iter = null;
		if (rows != null) {
			iter = rowIterator(rows.iterator());
		} else if (rowGen != null) {
			iter = rowIterator(rowGen.iterator());
		}

		List<Object[]> result = new ArrayList<Object[]>();
		while (iter.hasNext()) {
			result.add(iter.next());
		}

		return result;
    }

	public int getDbRowsCount() {
		return dbRowsCount;
	}

	public void setDbRowsCount(int dbRowsCount) {
		this.dbRowsCount = dbRowsCount;
	}

	public List<String[]> getStringifiedRows() {
		List<String[]> rows = new ArrayList<String[]>();
		Iterator<String[]> iter = stringifiedRowIterator();
		while (iter.hasNext()) {
			rows.add(iter.next());
		}

		return rows;
	}
    
    public Iterator<Object[]> rowIterator() {
    	if (rows != null) {
    		return rows.iterator();
    	} else if (rowGen != null) {
    		return rowIterator(rowGen.iterator());
    	}
    	
    	return null;
    }
    
    public Iterator<String[]> stringifiedRowIterator() {
    	Iterator<Object[]> iter = null;
    	if (rows != null) {
    		iter = rowIterator(rows.iterator());
    	} else if (rowGen != null) {
    		iter = rowIterator(rowGen.iterator());
    	}
    	
    	return stringifiedRowIterator(iter);
    }
    
    public void close() {
    	if (rowGen != null) {
    		rowGen.cleanup();
    	}
    	
    	if (rows != null) {
    		rows = null;
    	}
    }
    
    public String[] stringifyRow(Object[] row) {
        String result[] = new String[row.length];
        
        for(int j = 0; j < row.length; j++) {
            if (row[j] == null) {
                result[j] = null;
            } else if (row[j] instanceof Timestamp) {
                result[j] = toTime((Date)row[j]);
            } else if (row[j] instanceof Date){
                result[j] = toDate((Date)row[j]);
            } else if (Util.isOraTimestamp(row[j])) {
				Date dateObj = Util.getDateFromOraTimestamp(row[j]);
				result[j] = toTime(dateObj);
			} else if (row[j] instanceof Number) {
				result[j] = new BigDecimal(((Number)row[j]).doubleValue())
					.setScale(getResultColumns().get(j).getScale(), BigDecimal.ROUND_HALF_UP)
					.toString();
            } else {
            	result[j] = row[j].toString();
            }
        }

        return result;
    }
    
    private Iterator<Object[]> rowIterator(final Iterator<Object[]> iter) {
    	return new Iterator<Object[]>() {
			@Override
			public boolean hasNext() {
				return iter.hasNext();
			}

			@Override
			public Object[] next() {
				Object[] row = iter.next();
				if (screener != null) {
					row = screener.getScreenedRowData(resultColumns, row);
				}
				
				return row;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();				
			}    		
    	};
    }
    
    private Iterator<String[]> stringifiedRowIterator(final Iterator<Object[]> iter) {
    	return new Iterator<String[]>() {
			@Override
			public boolean hasNext() {					
				return iter.hasNext();
			}

			@Override
			public String[] next() {
				return stringifyRow(iter.next());
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}    			
   		};
    }

    private String toTime(Date input) {
		if (input == null) {
			return  null;
		}

		if (tsf != null) {
			return tsf.format(input);
		} else {
			return input.toInstant().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		}

	}

	private String toDate(Date input) {
		if (input == null) {
			return null;
		}

		if (sdf != null) {
			return sdf.format(input);
		} else {
			return input.toInstant().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE);
		}
	}
}
