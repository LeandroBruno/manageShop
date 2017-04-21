package com.javaunittest.handlers;

import com.javaunittest.data.input.InputRequest;
import com.javaunittest.data.output.OutputList;

public interface TaxHandler {

	/***
	 * 
	 * @param item
	 * @return Converts input list of items into the output data structure, calculates total cost and total taxes
	 */
	public OutputList convert(InputRequest item);

}
