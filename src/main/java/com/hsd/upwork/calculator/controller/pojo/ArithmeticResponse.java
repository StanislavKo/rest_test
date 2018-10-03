package com.hsd.upwork.calculator.controller.pojo;

public class ArithmeticResponse extends AbstractResponse {

	public ArithmeticResponse() {
	}

	public ArithmeticResponse(int code, String message) {
		super(code, message);
	}

	@Override
	public String toString() {
		return String.format("%s(%s)", getClass().getSimpleName(), super.toString());
	}

}
