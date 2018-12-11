package com.zandero.ffpojo.file.processor.record.handler;

import com.zandero.ffpojo.exception.RecordProcessorException;

public interface ErrorHandler {

	public void error(RecordProcessorException exception) throws RecordProcessorException;
	
}
