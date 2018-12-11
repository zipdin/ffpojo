package com.zandero.ffpojo.decorator;

import com.zandero.ffpojo.decorator.util.IntegerDecoratorUtil;
import com.zandero.ffpojo.exception.FieldDecoratorException;
import com.zandero.ffpojo.metadata.extra.ExtendedFieldDecorator;

public class InternalIntegerDecorator extends ExtendedFieldDecorator<Integer>{

	private IntegerDecoratorUtil util =  new IntegerDecoratorUtil();
	
	public String toString(Integer field) throws FieldDecoratorException {
		return util.toStringFromInteger(field);
	}

	public Integer fromString(String field) throws FieldDecoratorException {
		return util.fromStringToInteger(field);
	}

}
