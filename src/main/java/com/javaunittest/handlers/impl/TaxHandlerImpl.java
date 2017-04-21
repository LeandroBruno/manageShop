package com.javaunittest.handlers.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.javaunittest.data.enums.ItemTags;
import com.javaunittest.data.input.InputItem;
import com.javaunittest.data.input.InputRequest;
import com.javaunittest.data.output.OutputItem;
import com.javaunittest.data.output.OutputList;
import com.javaunittest.handlers.TaxHandler;

@Component
public class TaxHandlerImpl implements TaxHandler {

	private static final BigDecimal CONVERSION_COEFFICIENT = new BigDecimal(20);
	private static final Integer NON_EXEMPTED_PERCENTAGE = 10;
	private static final Integer IMPORTED_PERCENTAGE = 5;

	@Override
	public OutputList convert(InputRequest inputRequest) {
		OutputList output = new OutputList();
		List<OutputItem> outputList = inputRequest.getInputList().stream().map(item -> convertSingleItem(item)).collect(Collectors.toList());
		output.setResultList(outputList);
		output.setTotalCost(
				outputList.stream().map(item -> item.getTotalFinalValue()).reduce(BigDecimal.ZERO, BigDecimal::add));
		output.setTotalTaxes(
				outputList.stream().map(item -> item.getTotalTaxesValue()).reduce(BigDecimal.ZERO, BigDecimal::add));
		return output;
	}

	/***
	 * 
	 * @param item
	 * @return Converts the input item into an output item, set the taxes values and the final cost
	 */
	private OutputItem convertSingleItem(InputItem item) {
		OutputItem output = new OutputItem(item);
		BigDecimal taxesValue = applyTaxes(item);
		BigDecimal finalValue = output.getValue().add(taxesValue);
		output.setTaxesValue(taxesValue);
		output.setTotalTaxesValue(taxesValue.multiply(new BigDecimal(item.getQuantity())));
		output.setFinalValue(finalValue);
		output.setTotalFinalValue(finalValue.multiply(new BigDecimal(item.getQuantity())));
		return output;
	}

	/**
	 * 
	 * @param item
	 * @return calculates the value of the tax to apply to an item
	 */
	private BigDecimal applyTaxes(InputItem item) {
		BigDecimal taxesValue = calculateTaxesValue(item.getTag(), item.isImported(), item.getValue());
		return roundTaxedValue(taxesValue);
	}

	/**
	 * 
	 * @param tag
	 * @param isImported
	 * @param value
	 * @return the percentage value of the tax to apply to the item
	 */
	private BigDecimal calculateTaxesValue(ItemTags tag, boolean isImported, BigDecimal value) {
		int taxes = calculateExemptedTaxation(tag);
		taxes += calculateImportedTaxation(isImported);
		BigDecimal taxPercentage = new BigDecimal(taxes).divide(new BigDecimal(100));
		return value.multiply(taxPercentage);
	}

	/**
	 * 
	 * @param taxedValue
	 * @return the value of the tax rounded up to the closer 0,05 and with 2 digits
	 */
	private BigDecimal roundTaxedValue(BigDecimal taxedValue) {
		BigDecimal result = taxedValue.setScale(2, RoundingMode.DOWN);
		int numberOfDecimals = taxedValue.stripTrailingZeros().scale();
		if (numberOfDecimals > 1) {
			result = taxedValue.multiply(CONVERSION_COEFFICIENT);
			result = result.setScale(0, RoundingMode.UP).divide(CONVERSION_COEFFICIENT);
		}
		return result.setScale(2, RoundingMode.DOWN);
	}

	/**
	 * 
	 * @param tag 
	 * @return if the selected tag is exempted from sales taxes 0, otherwise the int value of the sales taxes
	 */
	private int calculateExemptedTaxation(ItemTags tag) {
		return tag.isExempted() ? 0 : NON_EXEMPTED_PERCENTAGE;
	}

	/**
	 * 
	 * @param isImported
	 * @return sales taxes for imported products if the product is imported, 0 otherwise
	 */
	private int calculateImportedTaxation(boolean isImported) {
		return isImported ? IMPORTED_PERCENTAGE : 0;
	}

}
