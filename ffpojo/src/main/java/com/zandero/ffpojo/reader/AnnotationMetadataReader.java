package com.zandero.ffpojo.reader;

import com.zandero.ffpojo.exception.MetadataReaderException;
import com.zandero.ffpojo.metadata.RecordDescriptor;

public abstract class AnnotationMetadataReader {
	
	protected Class<?> recordClazz;

	protected AnnotationMetadataReader(Class<?> recordClazz) {
		this.recordClazz = recordClazz;
	}
	
	public abstract RecordDescriptor readMetadata() throws MetadataReaderException;
	
}
