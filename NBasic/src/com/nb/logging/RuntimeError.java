package com.nb.logging;

public class RuntimeError extends Error {

	public RuntimeError(String exception, int status) {
		super(exception, status);
	}

}
