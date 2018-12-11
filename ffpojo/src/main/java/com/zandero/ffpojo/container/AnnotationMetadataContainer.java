package com.zandero.ffpojo.container;

import com.zandero.ffpojo.exception.MetadataContainerException;
import com.zandero.ffpojo.exception.MetadataNotFoundException;
import com.zandero.ffpojo.exception.MetadataReaderException;
import com.zandero.ffpojo.metadata.RecordDescriptor;
import com.zandero.ffpojo.reader.AnnotationMetadataReader;
import com.zandero.ffpojo.reader.AnnotationMetadataReaderFactory;


class AnnotationMetadataContainer extends BaseMetadataContainer implements MetadataContainer {

	public RecordDescriptor getRecordDescriptor(Class<?> recordClazz) throws MetadataContainerException {
		RecordDescriptor recordDescriptor = recordDescriptorByClazzMap.get(recordClazz);
		if (recordDescriptor == null) {
			AnnotationMetadataReader annotationMetadataReader;
			try {
				annotationMetadataReader = AnnotationMetadataReaderFactory.createAnnotationMetadataReader(recordClazz);
				recordDescriptor = annotationMetadataReader.readMetadata();
				recordDescriptorByClazzMap.put(recordClazz, recordDescriptor);
			} catch (MetadataNotFoundException e) {
				recordDescriptor = null;
			} catch (MetadataReaderException e) {
				throw new MetadataContainerException("Error while reading annotation metadata for class " + recordClazz, e);
			}
		}
		return recordDescriptor;
	}

}
