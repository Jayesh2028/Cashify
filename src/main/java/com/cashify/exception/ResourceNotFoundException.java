package com.cashify.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	String resourceNmae;
	String fieldName;
	String fieldValue;

	public String getResourceNmae() {
		return resourceNmae;
	}

	public void setResourceNmae(String resourceNmae) {
		this.resourceNmae = resourceNmae;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public ResourceNotFoundException(String resourceNmae, String fieldName, String fieldValue) {
		super(String.format("%s not found with this %s :%s", resourceNmae, fieldName, fieldValue));
		this.resourceNmae = resourceNmae;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public ResourceNotFoundException(String resourceNmae) {
		super(String.format(" Order not found for Order No : %s", resourceNmae));
		this.resourceNmae = resourceNmae;

	}

}
