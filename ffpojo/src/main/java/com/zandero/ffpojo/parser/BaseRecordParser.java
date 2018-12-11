package com.zandero.ffpojo.parser;

import com.zandero.ffpojo.metadata.RecordDescriptor;

abstract class BaseRecordParser implements RecordParser {

	protected RecordDescriptor recordDescriptor;
	
	protected BaseRecordParser(RecordDescriptor recordDescriptor) {
		this.recordDescriptor = recordDescriptor;
	}

	protected RecordDescriptor getRecordDescriptor() {
		return recordDescriptor;
	}
	
}
