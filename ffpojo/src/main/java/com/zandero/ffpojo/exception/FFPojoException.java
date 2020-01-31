package com.zandero.ffpojo.exception;

public class FFPojoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	long lineNumber;
	String line;

	public FFPojoException(String message) {
		super(message);
	}

	public FFPojoException(Throwable cause) {
		super(cause);
	}

	public FFPojoException(String message, Throwable cause) {
		super(message, cause);
	}

	public void setLineNumber(long lineNumber) {
		this.lineNumber = lineNumber;
	}

	public long getLineNumber() {
		return lineNumber;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public String getLine() {
		return line;
	}
}
