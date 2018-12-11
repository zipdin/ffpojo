package com.zandero.ffpojo.processor;

import com.zandero.ffpojo.file.processor.record.RecordProcessor;
import com.zandero.ffpojo.file.processor.record.event.RecordEvent;
import com.zandero.ffpojo.samples.pojo.Customer;

public class CustomerRecordProcessor implements RecordProcessor {

	public void processBody(RecordEvent event) {
		Customer cust = (Customer)event.getRecord();
		System.out.printf("[%d][%s][%s]\n", cust.getId(), cust.getName(), cust.getEmail());
	}

	public void processHeader(RecordEvent event) {
		// blank
	}

	public void processTrailer(RecordEvent event) {
		// blank
	}
	
}
