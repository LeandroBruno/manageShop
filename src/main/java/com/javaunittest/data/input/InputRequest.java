package com.javaunittest.data.input;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

public class InputRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4792103262982097180L;
	@Valid
	private List<InputItem> inputList;

	public InputRequest() {
	}

	public List<InputItem> getInputList() {
		return inputList;
	}

	public void setInputList(List<InputItem> inputList) {
		this.inputList = inputList;
	}
}
