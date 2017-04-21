package com.javaunittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.javaunittest.data.enums.ItemTags;
import com.javaunittest.data.input.InputItem;
import com.javaunittest.data.input.InputRequest;
import com.javaunittest.data.output.OutputList;
import com.javaunittest.handlers.impl.TaxHandlerImpl;

@RunWith(MockitoJUnitRunner.class)
public class TaxHandlerImplTest {

	private TaxHandlerImpl taxHandlerImpl;

	// INPUTS
	private final static String INPUT_NAME_1 = "Book";
	private final static String INPUT_NAME_2 = "music CD";
	private final static String INPUT_NAME_3 = "chocolate bar";
	private final static String INPUT_NAME_4 = "imported box of chocolates";
	private final static String INPUT_NAME_5 = "imported bottle of perfume";
	private final static String INPUT_NAME_6 = "imported bottle of perfume";
	private final static String INPUT_NAME_7 = "bottle of perfume";
	private final static String INPUT_NAME_8 = "packet of headache pills";
	private final static String INPUT_NAME_9 = "box of imported chocolates";
	private final static String INPUT_NAME_10 = "music CD stock";

	private final static ItemTags INPUT_TAG_1 = ItemTags.BOOK;
	private final static ItemTags INPUT_TAG_2 = ItemTags.MUSIC;
	private final static ItemTags INPUT_TAG_3 = ItemTags.FOOD;
	private final static ItemTags INPUT_TAG_4 = ItemTags.FOOD;
	private final static ItemTags INPUT_TAG_5 = ItemTags.PERFUME;
	private final static ItemTags INPUT_TAG_6 = ItemTags.PERFUME;
	private final static ItemTags INPUT_TAG_7 = ItemTags.PERFUME;
	private final static ItemTags INPUT_TAG_8 = ItemTags.MEDICAL;
	private final static ItemTags INPUT_TAG_9 = ItemTags.FOOD;
	private final static ItemTags INPUT_TAG_10 = ItemTags.MUSIC;

	private final static int INPUT_QUANTITY_1 = 1;
	private final static int INPUT_QUANTITY_2 = 1;
	private final static int INPUT_QUANTITY_3 = 1;
	private final static int INPUT_QUANTITY_4 = 1;
	private final static int INPUT_QUANTITY_5 = 1;
	private final static int INPUT_QUANTITY_6 = 1;
	private final static int INPUT_QUANTITY_7 = 1;
	private final static int INPUT_QUANTITY_8 = 1;
	private final static int INPUT_QUANTITY_9 = 1;
	private final static int INPUT_QUANTITY_10 = 5;

	private final static BigDecimal INPUT_VALUE_1 = new BigDecimal("12.49");
	private final static BigDecimal INPUT_VALUE_2 = new BigDecimal("14.99");
	private final static BigDecimal INPUT_VALUE_3 = new BigDecimal("0.85");
	private final static BigDecimal INPUT_VALUE_4 = new BigDecimal("10.00");
	private final static BigDecimal INPUT_VALUE_5 = new BigDecimal("47.50");
	private final static BigDecimal INPUT_VALUE_6 = new BigDecimal("27.99");
	private final static BigDecimal INPUT_VALUE_7 = new BigDecimal("18.99");
	private final static BigDecimal INPUT_VALUE_8 = new BigDecimal("9.75");
	private final static BigDecimal INPUT_VALUE_9 = new BigDecimal("11.25");
	private final static BigDecimal INPUT_VALUE_10 = new BigDecimal("14.99");

	private final static boolean INPUT_IMPORTED_1 = false;
	private final static boolean INPUT_IMPORTED_2 = false;
	private final static boolean INPUT_IMPORTED_3 = false;
	private final static boolean INPUT_IMPORTED_4 = true;
	private final static boolean INPUT_IMPORTED_5 = true;
	private final static boolean INPUT_IMPORTED_6 = true;
	private final static boolean INPUT_IMPORTED_7 = false;
	private final static boolean INPUT_IMPORTED_8 = false;
	private final static boolean INPUT_IMPORTED_9 = true;
	private final static boolean INPUT_IMPORTED_10 = false;

	private InputItem inputItem1;
	private InputItem inputItem2;
	private InputItem inputItem3;
	private InputItem inputItem4;
	private InputItem inputItem5;
	private InputItem inputItem6;
	private InputItem inputItem7;
	private InputItem inputItem8;
	private InputItem inputItem9;
	private InputItem inputItem10;

	private InputRequest inputRequest1;
	private List<InputItem> inputList1;
	private InputRequest inputRequest2;
	private List<InputItem> inputList2;
	private InputRequest inputRequest3;
	private List<InputItem> inputList3;
	private InputRequest inputRequest4;
	private List<InputItem> inputList4;

	// OUTPUT
	private final static BigDecimal OUTPUT_TAXED_VALUE_1 = new BigDecimal("12.49");
	private final static BigDecimal OUTPUT_TAXED_VALUE_2 = new BigDecimal("16.49");
	private final static BigDecimal OUTPUT_TAXED_VALUE_3 = new BigDecimal("0.85");
	private final static BigDecimal OUTPUT_TAXED_VALUE_4 = new BigDecimal("10.50");
	private final static BigDecimal OUTPUT_TAXED_VALUE_5 = new BigDecimal("54.65");
	private final static BigDecimal OUTPUT_TAXED_VALUE_6 = new BigDecimal("32.19");
	private final static BigDecimal OUTPUT_TAXED_VALUE_7 = new BigDecimal("20.89");
	private final static BigDecimal OUTPUT_TAXED_VALUE_8 = new BigDecimal("9.75");
	private final static BigDecimal OUTPUT_TAXED_VALUE_9 = new BigDecimal("11.85");
	private final static BigDecimal OUTPUT_TAXED_VALUE_10 = new BigDecimal("16.49");

	private final static BigDecimal OUTPUT_ITEM_TAXES_VALUE_1 = new BigDecimal("0.00");
	private final static BigDecimal OUTPUT_ITEM_TAXES_VALUE_2 = new BigDecimal("1.50");
	private final static BigDecimal OUTPUT_ITEM_TAXES_VALUE_3 = new BigDecimal("0.00");
	private final static BigDecimal OUTPUT_ITEM_TAXES_VALUE_4 = new BigDecimal("0.50");
	private final static BigDecimal OUTPUT_ITEM_TAXES_VALUE_5 = new BigDecimal("7.15");
	private final static BigDecimal OUTPUT_ITEM_TAXES_VALUE_6 = new BigDecimal("4.20");
	private final static BigDecimal OUTPUT_ITEM_TAXES_VALUE_7 = new BigDecimal("1.90");
	private final static BigDecimal OUTPUT_ITEM_TAXES_VALUE_8 = new BigDecimal("0.00");
	private final static BigDecimal OUTPUT_ITEM_TAXES_VALUE_9 = new BigDecimal("0.60");
	private final static BigDecimal OUTPUT_ITEM_TAXES_VALUE_10 = new BigDecimal("1.50");

	private final static BigDecimal OUTPUT_TOTAL_TAXES_1 = new BigDecimal("1.50");
	private final static BigDecimal OUTPUT_TOTAL_TAXES_2 = new BigDecimal("7.65");
	private final static BigDecimal OUTPUT_TOTAL_TAXES_3 = new BigDecimal("6.70");
	private final static BigDecimal OUTPUT_TOTAL_TAXES_4 = new BigDecimal("7.50");

	private final static BigDecimal OUTPUT_TOTAL_COST_1 = new BigDecimal("29.83");
	private final static BigDecimal OUTPUT_TOTAL_COST_2 = new BigDecimal("65.15");
	private final static BigDecimal OUTPUT_TOTAL_COST_3 = new BigDecimal("74.68");
	private final static BigDecimal OUTPUT_TOTAL_COST_4 = OUTPUT_TAXED_VALUE_10.multiply(new BigDecimal(INPUT_QUANTITY_10));
	
	@Before
	public void setUp() {

		taxHandlerImpl = new TaxHandlerImpl();

		inputRequest1 = new InputRequest();
		inputItem1 = new InputItem(INPUT_NAME_1, INPUT_VALUE_1, INPUT_QUANTITY_1, INPUT_TAG_1, INPUT_IMPORTED_1);

		inputItem2 = new InputItem(INPUT_NAME_2, INPUT_VALUE_2, INPUT_QUANTITY_2, INPUT_TAG_2, INPUT_IMPORTED_2);

		inputItem3 = new InputItem(INPUT_NAME_3, INPUT_VALUE_3, INPUT_QUANTITY_3, INPUT_TAG_3, INPUT_IMPORTED_3);

		inputList1 = Arrays.asList(inputItem1, inputItem2, inputItem3);
		inputRequest1.setInputList(inputList1);

		inputRequest2 = new InputRequest();

		inputItem4 = new InputItem(INPUT_NAME_4, INPUT_VALUE_4, INPUT_QUANTITY_4, INPUT_TAG_4, INPUT_IMPORTED_4);

		inputItem5 = new InputItem(INPUT_NAME_5, INPUT_VALUE_5, INPUT_QUANTITY_5, INPUT_TAG_5, INPUT_IMPORTED_5);

		inputList2 = Arrays.asList(inputItem4, inputItem5);
		inputRequest2.setInputList(inputList2);

		inputRequest3 = new InputRequest();

		inputItem6 = new InputItem(INPUT_NAME_6, INPUT_VALUE_6, INPUT_QUANTITY_6, INPUT_TAG_6, INPUT_IMPORTED_6);

		inputItem7 = new InputItem(INPUT_NAME_7, INPUT_VALUE_7, INPUT_QUANTITY_7, INPUT_TAG_7, INPUT_IMPORTED_7);

		inputItem8 = new InputItem(INPUT_NAME_8, INPUT_VALUE_8, INPUT_QUANTITY_8, INPUT_TAG_8, INPUT_IMPORTED_8);

		inputItem9 = new InputItem(INPUT_NAME_9, INPUT_VALUE_9, INPUT_QUANTITY_9, INPUT_TAG_9, INPUT_IMPORTED_9);

		inputList3 = Arrays.asList(inputItem6, inputItem7, inputItem8, inputItem9);
		inputRequest3.setInputList(inputList3);
		
		inputRequest4 = new InputRequest();
		inputItem10 = new InputItem(INPUT_NAME_10, INPUT_VALUE_10, INPUT_QUANTITY_10, INPUT_TAG_10, INPUT_IMPORTED_10);

		inputList4 = Arrays.asList(inputItem10);
		inputRequest4.setInputList(inputList4);
	}

	@Test
	public void testTaxHandlerCorrectnessScenario1() {
		OutputList output = taxHandlerImpl.convert(inputRequest1);
		assertNotNull(output);
		assertNotNull(output.getResultList());
		assertEquals(3, output.getResultList().size());
		assertEquals(OUTPUT_TAXED_VALUE_1, output.getResultList().get(0).getFinalValue());
		assertEquals(OUTPUT_TAXED_VALUE_2, output.getResultList().get(1).getFinalValue());
		assertEquals(OUTPUT_TAXED_VALUE_3, output.getResultList().get(2).getFinalValue());
		assertEquals(OUTPUT_ITEM_TAXES_VALUE_1, output.getResultList().get(0).getTaxesValue());
		assertEquals(OUTPUT_ITEM_TAXES_VALUE_2, output.getResultList().get(1).getTaxesValue());
		assertEquals(OUTPUT_ITEM_TAXES_VALUE_3, output.getResultList().get(2).getTaxesValue());
		assertEquals(OUTPUT_TOTAL_COST_1, output.getTotalCost());
		assertEquals(OUTPUT_TOTAL_TAXES_1, output.getTotalTaxes());
	}

	@Test
	public void testTaxHandlerCorrectnessScenario2() {
		OutputList output = taxHandlerImpl.convert(inputRequest2);
		assertNotNull(output);
		assertNotNull(output.getResultList());
		assertEquals(2, output.getResultList().size());
		assertEquals(OUTPUT_TAXED_VALUE_4, output.getResultList().get(0).getFinalValue());
		assertEquals(OUTPUT_TAXED_VALUE_5, output.getResultList().get(1).getFinalValue());
		assertEquals(OUTPUT_ITEM_TAXES_VALUE_4, output.getResultList().get(0).getTaxesValue());
		assertEquals(OUTPUT_ITEM_TAXES_VALUE_5, output.getResultList().get(1).getTaxesValue());
		assertEquals(OUTPUT_TOTAL_COST_2, output.getTotalCost());
		assertEquals(OUTPUT_TOTAL_TAXES_2, output.getTotalTaxes());
	}

	@Test
	public void testTaxHandlerCorrectnessScenario3() {
		OutputList output = taxHandlerImpl.convert(inputRequest3);
		assertNotNull(output);
		assertNotNull(output.getResultList());
		assertEquals(4, output.getResultList().size());
		assertEquals(OUTPUT_TAXED_VALUE_6, output.getResultList().get(0).getFinalValue());
		assertEquals(OUTPUT_TAXED_VALUE_7, output.getResultList().get(1).getFinalValue());
		assertEquals(OUTPUT_TAXED_VALUE_8, output.getResultList().get(2).getFinalValue());
		assertEquals(OUTPUT_TAXED_VALUE_9, output.getResultList().get(3).getFinalValue());
		assertEquals(OUTPUT_ITEM_TAXES_VALUE_6, output.getResultList().get(0).getTaxesValue());
		assertEquals(OUTPUT_ITEM_TAXES_VALUE_7, output.getResultList().get(1).getTaxesValue());
		assertEquals(OUTPUT_ITEM_TAXES_VALUE_8, output.getResultList().get(2).getTaxesValue());
		assertEquals(OUTPUT_ITEM_TAXES_VALUE_9, output.getResultList().get(3).getTaxesValue());
		assertEquals(OUTPUT_TOTAL_COST_3, output.getTotalCost());
		assertEquals(OUTPUT_TOTAL_TAXES_3, output.getTotalTaxes());
	}


	@Test
	public void testTaxHandlerCorrectnessQuantity() {
		OutputList output = taxHandlerImpl.convert(inputRequest4);
		assertNotNull(output);
		assertNotNull(output.getResultList());
		assertEquals(1, output.getResultList().size());
		assertEquals(OUTPUT_TAXED_VALUE_10, output.getResultList().get(0).getFinalValue());
		assertEquals(OUTPUT_ITEM_TAXES_VALUE_10, output.getResultList().get(0).getTaxesValue());
		assertEquals(OUTPUT_TOTAL_COST_4, output.getTotalCost());
		assertEquals(OUTPUT_TOTAL_TAXES_4, output.getTotalTaxes());
	}

}
