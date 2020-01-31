package com.zandero.ffpojo.decorator;

import java.math.BigDecimal;

import com.zandero.ffpojo.decorator.util.FloatPointDecoratorUtil;
import com.zandero.ffpojo.metadata.extra.ExtendedFieldDecorator;
import com.zandero.ffpojo.util.StringUtil;

public class InternalBigDecimalDecorator extends ExtendedFieldDecorator<BigDecimal> {
 
	private static final int DEFAULT_PRECISION = 2;
	private final FloatPointDecoratorUtil floatPointDecoratorUtil;
	
	public InternalBigDecimalDecorator() {
		this(DEFAULT_PRECISION);
	}
	
	public InternalBigDecimalDecorator(int precision) {
		floatPointDecoratorUtil = new FloatPointDecoratorUtil(precision);
	}

	public int getPrecision() {
		return floatPointDecoratorUtil.getPrecision();
	}

	public String toString(BigDecimal value){
		return floatPointDecoratorUtil.toString(value);
	}

	public BigDecimal fromString(String value){
		if (StringUtil.isNullOrEmpty(value)) return null;
		return floatPointDecoratorUtil.fromString(value);
	}

	/**
	 * Return the constructor parameters type
	 * @return
	 */
	public static Class<?>[] getTypesConstructorExtended(){
		return new Class[]{int.class};
	}

	/**
	 * Return the methods names in annotation that contains the values to call the constructor
	 * @return
	 */
	public static String[] getMethodContainsContstructorValues(){
		return new String[]{"precision"};
	}
}
