package edu.common.dynamicextensions.query.ast;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import edu.common.dynamicextensions.domain.nui.DataType;

public class DateRangeFuncNode extends ExpressionNode implements Serializable {
	public enum RangeType {
		LAST_CAL_QTR("last_cal_qtr"),

		LAST_QTR("last_qtr"),

		LAST_CAL_MONTH("last_cal_month"),

		LAST_MONTH("last_month");

		private String type;

		RangeType(String type) {
			this.type = type;
		}

		public static RangeType from(String type) {
			for (RangeType t : RangeType.values()) {
				if (t.type.equals(type)) {
					return t;
				}
			}

			throw new IllegalArgumentException("Invalid range type: " + type);
		}
	}

	private ExpressionNode dateExpr;

	private RangeType rangeType;

	private int range = 1;


	@Override
	public DataType getType() {
		return DataType.BOOLEAN;
	}

	@Override
	public ExpressionNode copy() {
		DateRangeFuncNode copy = new DateRangeFuncNode();
		super.copy(this, copy);
		copy.setDateExpr(getDateExpr().copy());
		copy.setRangeType(getRangeType());
		copy.setRange(getRange());
		return copy;
	}

	@Override
	public String[] getFormNames() {
		return getDateExpr().getFormNames();
	}

	@Override
	public boolean isPhi() {
		return false;
	}

	@Override
	public Set<FieldNode> getFields() {
		return new HashSet<>(getDateExpr().getFields());
	}

	public ExpressionNode getDateExpr() {
		return dateExpr;
	}

	public void setDateExpr(ExpressionNode dateExpr) {
		this.dateExpr = dateExpr;
	}

	public RangeType getRangeType() {
		return rangeType;
	}

	public void setRangeType(RangeType rangeType) {
		this.rangeType = rangeType;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other == null || getClass() != other.getClass()) {
			return false;
		}

		DateRangeFuncNode that = (DateRangeFuncNode) other;

		if (getRange() != that.getRange()) {
			return false;
		}

		if (!getDateExpr().equals(that.getDateExpr())) {
			return false;
		}

		if (getRangeType() != that.getRangeType()) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = getDateExpr().hashCode();
		result = 31 * result + getRangeType().hashCode();
		result = 31 * result + getRange();
		return result;
	}
}
