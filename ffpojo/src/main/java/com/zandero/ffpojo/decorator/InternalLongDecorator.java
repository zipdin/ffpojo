package com.zandero.ffpojo.decorator;

import com.zandero.ffpojo.decorator.util.IntegerDecoratorUtil;
import com.zandero.ffpojo.exception.FieldDecoratorException;
import com.zandero.ffpojo.metadata.extra.ExtendedFieldDecorator;

public class InternalLongDecorator extends ExtendedFieldDecorator<Long>{

	private final IntegerDecoratorUtil util = new IntegerDecoratorUtil();
	
	public String toString(Long field) throws FieldDecoratorException {
		return util.toStringFromLong(field);
	}

	public Long fromString(String field) throws FieldDecoratorException {
		return util.fromStringToLong(field);
	}

}
