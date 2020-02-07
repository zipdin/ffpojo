package com.zandero.ffpojo.file.processor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.zandero.ffpojo.exception.FFPojoException;
import com.zandero.ffpojo.exception.RecordProcessorException;
import com.zandero.ffpojo.file.processor.record.RecordProcessor;
import com.zandero.ffpojo.file.processor.record.event.DefaultRecordEvent;
import com.zandero.ffpojo.file.processor.record.event.RecordEvent;
import com.zandero.ffpojo.file.reader.FlatFileReader;
import com.zandero.ffpojo.file.reader.RecordType;

public class ThreadPoolFlatFileProcessor extends BaseFlatFileProcessor implements FlatFileProcessor {

	private ExecutorService executor;
	private long timeOut;
	private TimeUnit unit;

	public ThreadPoolFlatFileProcessor(FlatFileReader flatFileReader, int threadPoolSize, long timeOut, TimeUnit unit) {
		super(flatFileReader);
		this.timeOut = timeOut;
		this.unit = unit;
		this.executor = Executors.newFixedThreadPool(threadPoolSize);
	}

	public void processFlatFile(final RecordProcessor processor) {
		try {
			for (final Object record : flatFileReader) {
				final RecordType recordType = flatFileReader.getRecordType();
				final RecordEvent event = new DefaultRecordEvent(record, flatFileReader.getRecordText(), flatFileReader.getRecordIndex());
				executor.execute(() -> {
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
				});
			}
			executor.shutdown();
			if (!executor.awaitTermination(timeOut, unit)) {
				throw new Exception("Thread pool timeout while reading from stream.");
			}
			endOfFileHandler.readComplete(flatFileReader.getRecordIndex());
		} catch (FFPojoException e) {
			e.setLine(flatFileReader.getRecordText());
			e.setLineNumber(flatFileReader.getRecordIndex());

			errorHandler.error(e);
			processFlatFile(processor);
		} catch (Exception e) {
			RecordProcessorException recordProcessorException = new RecordProcessorException(e);
			recordProcessorException.setLine(flatFileReader.getRecordText());
			recordProcessorException.setLineNumber(flatFileReader.getRecordIndex());

			errorHandler.error(recordProcessorException);
			processFlatFile(processor);
		}
	}
}
