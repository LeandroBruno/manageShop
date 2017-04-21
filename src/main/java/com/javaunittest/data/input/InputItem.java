package com.javaunittest.data.input;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.javaunittest.data.enums.ItemTags;

public class InputItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7087138140610721303L;
	
	@Size(min=3, max=150, message = "Name size must be between 3 and 150 character") 
	private String name;
	@DecimalMin(inclusive=false, value = "0.00", message = "Value must be greater than zero")
	private BigDecimal value;
	@Min(value=1l ,message="Quantity must be greater than zero" )
	private int quantity;
	@NotNull
	private ItemTags tag;
	
	private boolean imported;

	public InputItem(){
		
	}
	
	public InputItem(String name, BigDecimal value, int quantity, ItemTags tag, boolean imported) {
		this.name = name;
		this.value = value;
		this.quantity = quantity;
		this.tag = tag;
		this.imported = imported;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	public ItemTags getTag() {
		return tag;
	}

	public void setTag(ItemTags tag) {
		this.tag = tag;
	}

}
