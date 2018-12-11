package com.zandero.ffpojo.metadata.positional.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zandero.ffpojo.metadata.DefaultFieldDecorator;
import com.zandero.ffpojo.metadata.FieldDecorator;
import com.zandero.ffpojo.metadata.positional.PaddingAlign;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface PositionalField {
	int initialPosition();
	int finalPosition();
	PaddingAlign paddingAlign() default PaddingAlign.RIGHT;
	char paddingCharacter() default ' ';
	boolean trimOnRead() default true;
	Class<? extends FieldDecorator<?>> decorator() default DefaultFieldDecorator.class; 
}
