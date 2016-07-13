package edu.common.dynamicextensions.query.ast;

import java.io.Serializable;

public class OrderExprNode implements Node, Serializable {
	private static final long serialVersionUID = -1373438959428971179L;

	private ExpressionNode expr;

	private boolean descending = false;

	public ExpressionNode getExpr() {
		return expr;
	}

	public void setExpr(ExpressionNode expr) {
		this.expr = expr;
	}

	public boolean isDescending() {
		return descending;
	}

	public void setDescending(boolean descending) {
		this.descending = descending;
	}
}
