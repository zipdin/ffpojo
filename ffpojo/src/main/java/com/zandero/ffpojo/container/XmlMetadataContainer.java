package com.zandero.ffpojo.container;

import java.io.IOException;
import java.io.InputStream;

import com.zandero.ffpojo.exception.MetadataContainerException;
import com.zandero.ffpojo.exception.MetadataReaderException;
import com.zandero.ffpojo.metadata.RecordDescriptor;
import com.zandero.ffpojo.reader.XmlMetadataReader;


class XmlMetadataContainer extends BaseMetadataContainer implements MetadataContainer {

	public XmlMetadataContainer(InputStream xmlMetadataInputStream) throws MetadataContainerException {
		XmlMetadataReader xmlMetadataReader = new XmlMetadataReader(xmlMetadataInputStream);
		try {
			this.recordDescriptorByClazzMap = xmlMetadataReader.readMetadata();
			xmlMetadataInputStream.close();
		} catch (MetadataReaderException e) {
			throw new MetadataContainerException("Error while reading ffpojo-ofm xml metadata", e);
		} catch (IOException e) {
			throw new MetadataContainerException("Error while reading ffpojo-ofm xml metadata", e);
		}
	}
	
	public RecordDescriptor getRecordDescriptor(Class<?> recordClazz) {
		return recordDescriptorByClazzMap.get(recordClazz);
	}

}
