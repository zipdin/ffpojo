package com.zandero.ffpojo.file.processor.record.handler;

public interface EndOfFileHandler {
	
	void readComplete(long totalLinesRead);

}
