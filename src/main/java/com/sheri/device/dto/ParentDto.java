package com.sheri.device.dto;

import java.util.ArrayList;
import java.util.List;

public class ParentDto {
	private boolean hasError;
	private List<ErrorDto> errors=new ArrayList<ErrorDto>();
	public boolean hasError()
	{
		return hasError;
	}
	public void setHasError(boolean hasError) 
	{
		this.hasError = hasError;
	}
	
	public void addError(String resourceKey, String errorCode, String message)
	{
		hasError=true;
		ErrorDto errorDto=new ErrorDto(resourceKey, errorCode, message);
		errors.add(errorDto);
		
	}
	public List<ErrorDto> getErrors() {
		return errors;
	}
	
}
