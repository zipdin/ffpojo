package com.zandero.ffpojo.metadata.delimited.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.zandero.ffpojo.metadata.DefaultFieldDecorator;
import com.zandero.ffpojo.metadata.FieldDecorator;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface DelimitedField {
	int positionIndex();
	Class<? extends FieldDecorator<?>> decorator() default DefaultFieldDecorator.class;
}
