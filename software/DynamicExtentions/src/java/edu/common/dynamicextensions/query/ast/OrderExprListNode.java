package edu.common.dynamicextensions.query.ast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderExprListNode implements Node, Serializable {
	private static final long serialVersionUID = -6216319287644413822L;

	private List<OrderExprNode> exprs = new ArrayList<>();

	public List<OrderExprNode> getExprs() {
		return exprs;
	}

	public void setExprs(List<OrderExprNode> exprs) {
		this.exprs = exprs;
	}
}
