package com.zandero.ffpojo.container;

import com.zandero.ffpojo.exception.MetadataContainerException;
import com.zandero.ffpojo.metadata.RecordDescriptor;

public interface MetadataContainer {

	public RecordDescriptor getRecordDescriptor(Class<?> recordClazz) throws MetadataContainerException;
	
}
