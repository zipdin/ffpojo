package com.zandero.ffpojo.file.processor.record.handler;

import com.zandero.ffpojo.exception.FFPojoException;

public interface ErrorHandler {

	void error(FFPojoException exception) throws FFPojoException;

}
