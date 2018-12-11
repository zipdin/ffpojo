package com.zandero.ffpojo.decorator;

import java.math.BigInteger;

import com.zandero.ffpojo.decorator.util.IntegerDecoratorUtil;
import com.zandero.ffpojo.exception.FieldDecoratorException;
import com.zandero.ffpojo.metadata.extra.ExtendedFieldDecorator;

public class InternalBigIntegerDecorator extends ExtendedFieldDecorator<BigInteger>{

	private final IntegerDecoratorUtil util =  new IntegerDecoratorUtil();
	
	public String toString(BigInteger field) throws FieldDecoratorException {
		return util.toStringFromBigInteger(field);
	}

	public BigInteger fromString(String field) throws FieldDecoratorException {
		return util.fromStringToBigInteger(field);
	}

}
