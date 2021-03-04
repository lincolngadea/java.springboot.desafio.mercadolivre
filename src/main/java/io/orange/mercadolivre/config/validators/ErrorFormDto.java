package io.orange.mercadolivre.config.validators;

public class ErrorFormDto {

	private  String field;
	private  String error;
	private  Object rejectedError;

	public ErrorFormDto(String field, String error, Object rejectedError) {
		this.field = field;
		this.error = error;
		this.rejectedError = rejectedError;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}

	public Object getRejectedError() {
		return rejectedError;
	}
}
