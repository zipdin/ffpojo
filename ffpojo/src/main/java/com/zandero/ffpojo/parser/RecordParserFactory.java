package com.zandero.ffpojo.parser;

import com.zandero.ffpojo.metadata.RecordDescriptor;
import com.zandero.ffpojo.metadata.delimited.DelimitedRecordDescriptor;
import com.zandero.ffpojo.metadata.positional.PositionalRecordDescriptor;

public class RecordParserFactory {

	public static RecordParser createRecordParser(RecordDescriptor recordDescriptor) {
		if (recordDescriptor instanceof PositionalRecordDescriptor) {
			return new PositionalRecordParser((PositionalRecordDescriptor)recordDescriptor);
		} else if (recordDescriptor instanceof DelimitedRecordDescriptor) {
			return new DelimitedRecordParser((DelimitedRecordDescriptor)recordDescriptor);
		} else {
			throw new IllegalArgumentException("RecordParser not found for class " + recordDescriptor.getClass());
		}
	}
	
}
