package com.zandero.ffpojo.file.processor;

import com.zandero.ffpojo.file.processor.record.RecordProcessor;
import com.zandero.ffpojo.file.processor.record.handler.EndOfFileHandler;
import com.zandero.ffpojo.file.processor.record.handler.ErrorHandler;


public interface FlatFileProcessor {

	void processFlatFile(RecordProcessor processor);
	
	void setErrorHandler(ErrorHandler errorHandler);
	
	void setEndOfFileHandler(EndOfFileHandler endOfFileHandler);
	
}
