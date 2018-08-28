package com.events.api.controller;

import java.security.InvalidParameterException;
import java.util.StringJoiner;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class Controller {

	public void verifyInvalidParam(BindingResult result) {
		if (result.hasErrors()) {
			StringJoiner str = new StringJoiner(", ", "[", "]");
			for (FieldError error : result.getFieldErrors()) {
				str.add(error.getField() + ": " + error.getDefaultMessage());
			}
			throw new InvalidParameterException(str.toString());
		}
	}

}
