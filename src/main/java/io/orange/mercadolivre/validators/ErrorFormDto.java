package io.orange.mercadolivre.validators;

public class ErrorFormDto {

	private final String field;
	private final String error;

	public ErrorFormDto(String field, String error) {
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}

}
