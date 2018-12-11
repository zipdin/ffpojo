package com.zandero.ffpojo.file.processor;

import com.zandero.ffpojo.file.processor.record.RecordProcessor;
import com.zandero.ffpojo.file.processor.record.handler.ErrorHandler;


public interface FlatFileProcessor {

	public void processFlatFile(RecordProcessor processor);
	
	public void setErrorHandler(ErrorHandler errorHandler);
	
}
