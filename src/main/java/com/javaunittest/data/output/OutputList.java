package com.javaunittest.data.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OutputList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -569620403104017028L;
	
	private List<OutputItem> resultList = new ArrayList<>();
	private BigDecimal totalTaxes;
	private BigDecimal totalCost;
	
	private List<String> validationErrorList;
	
	public List<OutputItem> getResultList() {
		return resultList;
	}
	public void setResultList(List<OutputItem> resultList) {
		this.resultList = resultList;
	}
	public BigDecimal getTotalTaxes() {
		return totalTaxes;
	}
	public void setTotalTaxes(BigDecimal totalTaxes) {
		this.totalTaxes = totalTaxes;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	public List<String> getValidationErrorList() {
		return validationErrorList;
	}
	public void setValidationErrorList(List<String> validationErrorList) {
		this.validationErrorList = validationErrorList;
	}
	
	
	
}
