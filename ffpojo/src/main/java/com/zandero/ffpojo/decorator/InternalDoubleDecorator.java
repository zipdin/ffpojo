package com.zandero.ffpojo.decorator;

import com.zandero.ffpojo.decorator.util.FloatPointDecoratorUtil;
import com.zandero.ffpojo.metadata.extra.ExtendedFieldDecorator;
import com.zandero.ffpojo.util.StringUtil;

public class InternalDoubleDecorator extends ExtendedFieldDecorator<Double> {

	private static final int DEFAULT_PRECISION = 2;
	private final FloatPointDecoratorUtil floatPointDecoratorUtil;
	
	public InternalDoubleDecorator() {
		this(DEFAULT_PRECISION);
	}
	
	public InternalDoubleDecorator(int precision) {
		floatPointDecoratorUtil = new FloatPointDecoratorUtil(precision);
	}

	public int getPrecision() {
		return floatPointDecoratorUtil.getPrecision();
	}

	public String toString(Double value){
		return floatPointDecoratorUtil.toString(value);
	}

	public Double fromString(String value){
		if (StringUtil.isNullOrEmpty(value)) return null;
		return floatPointDecoratorUtil.fromString(value).doubleValue();
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
