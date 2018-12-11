package com.zandero.ffpojo.dsl;

import java.util.List;

@SuppressWarnings("all")
public interface AddClassBuilder {

	public FFReaderBuilder withRecordClass(Class<?> clazz);
	
	public FFReaderBuilder withRecordClasses(List<Class<?>> clazz);
	
}
