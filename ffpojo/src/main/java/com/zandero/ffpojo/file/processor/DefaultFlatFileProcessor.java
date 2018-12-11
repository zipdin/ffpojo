package com.zandero.ffpojo.file.processor;

import com.zandero.ffpojo.exception.RecordProcessorException;
import com.zandero.ffpojo.file.processor.record.RecordProcessor;
import com.zandero.ffpojo.file.processor.record.event.DefaultRecordEvent;
import com.zandero.ffpojo.file.processor.record.event.RecordEvent;
import com.zandero.ffpojo.file.reader.FlatFileReader;
import com.zandero.ffpojo.file.reader.RecordType;


public class DefaultFlatFileProcessor extends BaseFlatFileProcessor implements FlatFileProcessor {

	public DefaultFlatFileProcessor(FlatFileReader flatFileReader) {
		super(flatFileReader);
	}
	
	public void processFlatFile(RecordProcessor processor) {
		for(Object record : flatFileReader) {
			RecordType recordType = flatFileReader.getRecordType();
			RecordEvent event = new DefaultRecordEvent(record, flatFileReader.getRecordText(), flatFileReader.getRecordIndex());
			try {
				if (recordType == RecordType.HEADER) {
					processor.processHeader(event);				
				} else if (recordType == RecordType.BODY) {
					processor.processBody(event);
				} else if (recordType == RecordType.TRAILER) {
					processor.processTrailer(event);
				}
			} catch (RecordProcessorException e) {
				try {
					errorHandler.error(e);
				} catch (RecordProcessorException exThrownByErrorHandler) {
					exThrownByErrorHandler.printStackTrace();
				}
			}
		}
	}
	
}
