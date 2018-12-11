package com.zandero.ffpojo.file.processor;

import com.zandero.ffpojo.file.processor.record.handler.DefaultErrorHandler;
import com.zandero.ffpojo.file.processor.record.handler.ErrorHandler;
import com.zandero.ffpojo.file.reader.FlatFileReader;


abstract class BaseFlatFileProcessor implements FlatFileProcessor {

	protected FlatFileReader flatFileReader;
	protected ErrorHandler errorHandler;

	protected BaseFlatFileProcessor(FlatFileReader flatFileReader) {
		if (flatFileReader == null || flatFileReader.isClosed()) {
			throw new IllegalArgumentException("FlatFileReader object is null or closed");
		}
		this.errorHandler = new DefaultErrorHandler();
		this.flatFileReader = flatFileReader;
	}
	
	public void setErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}
	
}
