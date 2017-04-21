package com.javaunittest.data.output;

import java.math.BigDecimal;

import com.javaunittest.data.enums.ItemTags;
import com.javaunittest.data.input.InputItem;

public class OutputItem extends InputItem {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6859935439216572519L;

	private BigDecimal taxesValue;
	private BigDecimal totalTaxesValue;
	private BigDecimal finalValue;
	private BigDecimal totalFinalValue;

	public OutputItem(String name, BigDecimal value, int quantity, ItemTags tag, boolean imported) {
		super(name, value, quantity, tag, imported);
	}

	public OutputItem(InputItem inputItem) {
		super(inputItem.getName(), inputItem.getValue(), inputItem.getQuantity(), inputItem.getTag(),
				inputItem.isImported());
	}

	public BigDecimal getFinalValue() {
		return finalValue;
	}

	public void setFinalValue(BigDecimal finalValue) {
		this.finalValue = finalValue;
	}

	public BigDecimal getTaxesValue() {
		return taxesValue;
	}

	public void setTaxesValue(BigDecimal taxesValue) {
		this.taxesValue = taxesValue;
	}

	public BigDecimal getTotalTaxesValue() {
		return totalTaxesValue;
	}

	public void setTotalTaxesValue(BigDecimal totalTaxesValue) {
		this.totalTaxesValue = totalTaxesValue;
	}

	public BigDecimal getTotalFinalValue() {
		return totalFinalValue;
	}

	public void setTotalFinalValue(BigDecimal totalFinalValue) {
		this.totalFinalValue = totalFinalValue;
	}

}
