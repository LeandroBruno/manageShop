package com.javaunittest.controllers;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.javaunittest.data.enums.ItemTags;
import com.javaunittest.data.input.InputRequest;
import com.javaunittest.data.output.OutputList;
import com.javaunittest.handlers.TaxHandler;

@Controller
@RequestMapping("/manageCatalog")
public class ManageCatalogController {

	private static final String HOME_LOCATION = "home";

	@Autowired
	private TaxHandler taxHandler;

	@RequestMapping(value={"", "/"})
	@ResponseStatus(HttpStatus.OK)
	public String index() {
		return HOME_LOCATION;
	}

	/**
	 * 
	 * @param input
	 * @param bindingResult
	 * @return Calculatest the taxes and values for all input item. In case of validation errors, it will return the list errors to the front end
	 */
	@RequestMapping(value = "/sendList", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public OutputList getCatalogList(@RequestBody @Valid InputRequest input, BindingResult bindingResult) {

		OutputList output = new OutputList();
		if (bindingResult.hasErrors()) {
			output.setValidationErrorList(
					bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList()));
			return output;
		}
		return taxHandler.convert(input);
	}

	@RequestMapping(value = "/getTags", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public ItemTags[] getTags() {
		return ItemTags.values();
	}
}