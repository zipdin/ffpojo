package com.zandero.ffpojo.metadata;

import com.zandero.ffpojo.exception.FieldDecoratorException;



public interface FieldDecorator<T> {

	public String toString(T field) throws FieldDecoratorException;
	
	public T fromString(String field) throws FieldDecoratorException;
	
}
