package com.zandero.ffpojo.file.reader;

import java.io.IOException;
import java.util.Iterator;

public interface FlatFileReader extends Iterator<Object>, Iterable<Object> {

	void reset() throws IOException;
	void close() throws IOException;
	boolean isResetSupported();
	boolean isClosed();

	RecordType getRecordType();
	String getRecordText();
	long getRecordIndex();

	// Iterator
	boolean hasNext();
	Object next();
	@Deprecated
	void remove();

	// Iterable
	Iterator<Object> iterator();

}
