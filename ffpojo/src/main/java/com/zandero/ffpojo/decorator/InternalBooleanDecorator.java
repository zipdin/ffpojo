package com.zandero.ffpojo.decorator;

import java.lang.annotation.Annotation;

import com.zandero.ffpojo.exception.FieldDecoratorException;
import com.zandero.ffpojo.metadata.extra.ExtendedFieldDecorator;

public class InternalBooleanDecorator extends ExtendedFieldDecorator<Boolean>{

	private String trueIdentifier;
	private String falseIdentifier;

	/**
	 *
	 * @param trueItendifier
	 * @param falseIdentifier
	 */
	public InternalBooleanDecorator(String trueItendifier, String falseIdentifier) {
		this.trueIdentifier =  trueItendifier;
		this.falseIdentifier =  falseIdentifier;
	}

	/**
	 *
	 * @param field
	 * @return TODO
	 * @throws FieldDecoratorException
	 */
	public String toString(Boolean field) throws FieldDecoratorException {
		if (field==null) return null;
		if (field.booleanValue()){
			return trueIdentifier;
		}
		return falseIdentifier;
	}

	/**
	 *
	 * @param field
	 * @return TODO
	 * @throws FieldDecoratorException
	 */
	public Boolean fromString(String field) throws FieldDecoratorException {
		if (field== null) return null;
		return (field.trim().equalsIgnoreCase(trueIdentifier));
	}

	/**
	 *
	 * @return TODO
	 */
	public static Class<?>[] getTypesConstructorExtended(){
		return new Class[]{String.class, String.class};
	}
	
	/**
	 * Return the methods names in annotation that contains the values to call the constructor
	 * @return TODO
	 */
	public static String[] getMethodContainsContstructorValues(){
		return new String[]{"trueIdentifier", "falseIdentifier"};
	}
	
	/**
	 * Return the Annotation linked with the decoration
	 * @return TODO
	 */
	public static Class<? extends Annotation> annotationLinked(){
		return Annotation.class;
	}

}
