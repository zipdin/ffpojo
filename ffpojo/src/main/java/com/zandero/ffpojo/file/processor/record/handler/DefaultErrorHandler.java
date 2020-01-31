package com.zandero.ffpojo.file.processor.record.handler;

import com.zandero.ffpojo.exception.FFPojoException;
import com.zandero.ffpojo.exception.RecordProcessorException;

public class DefaultErrorHandler implements ErrorHandler {

	public void error(FFPojoException exception) throws RecordProcessorException {
		throw exception;
	}
}
