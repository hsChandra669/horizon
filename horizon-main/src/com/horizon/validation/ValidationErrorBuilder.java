package com.horizon.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidationErrorBuilder {
/*
    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
        for (ObjectError objectError : errors.getAllErrors()) {
            error.addValidationError(objectError.getDefaultMessage());
        }
        return error;
    }*/

	public static Map<String, String> populateErrorMap(BindingResult bindingResult, MessageSource messageSource) {
		 Map<String ,String> errors=new HashMap<String, String>();
         List<FieldError> fieldErrors = bindingResult.getFieldErrors();
         for (FieldError fieldError : fieldErrors) {
        	 String[] resolveMessageCodes = bindingResult.resolveMessageCodes(fieldError.getCode());
                String string = resolveMessageCodes[0];
                String message = messageSource.getMessage(string+"."+fieldError.getField(),
                		new Object[]{fieldError.getRejectedValue()}, null);
                errors.put(fieldError.getField(), message);
         }
         return errors;
	}


}