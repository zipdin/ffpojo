package com.zandero.ffpojo.decorator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zandero.ffpojo.exception.FieldDecoratorException;
import com.zandero.ffpojo.metadata.extra.ExtendedFieldDecorator;
import com.zandero.ffpojo.util.StringUtil;

public class InternalDateDecorator extends ExtendedFieldDecorator<Date> {

	private static final String DEFAULT_FORMAT = "dd/MM/yyyy";
	private final SimpleDateFormat sdf;
	
	public InternalDateDecorator(String format) {
		sdf = new SimpleDateFormat(format);
	}
	public InternalDateDecorator(){
		this(DEFAULT_FORMAT);
	}

	public String toString(Date field) throws FieldDecoratorException {		
		if (field == null) return null;
		return sdf.format(field);
	}

	public Date fromString(String field) throws FieldDecoratorException {
		if(StringUtil.isNullOrEmpty(field)) return null;
		try {
			return sdf.parse(field);
		} catch (ParseException e) {
			throw new FieldDecoratorException(e);
		}		
	}
	
	public static Class<?>[] getTypesConstructorExtended() {
		return new Class<?>[]{String.class};
	}
	
	public static String[] getMethodContainsContstructorValues(){
		return new String[]{"dateFormat"};
	}

}
