package com.zandero.ffpojo.decorator;

import com.zandero.ffpojo.exception.FieldDecoratorException;
import com.zandero.ffpojo.metadata.extra.ExtendedFieldDecorator;
import com.zandero.ffpojo.metadata.positional.annotation.EnumerationType;
import com.zandero.ffpojo.util.StringUtil;

/**
 * Created by William on 02/11/15.
 */
public class InternalEnumDecorator extends ExtendedFieldDecorator<Enum<?>> {

    private EnumerationType enumerationType;
    private Class<? extends  Enum> clazzEnum;

    public InternalEnumDecorator(EnumerationType enumerationType, Class<? extends  Enum> enumeration ) {
        this.enumerationType = enumerationType;
        this.clazzEnum =  enumeration;
    }

    public String toString(Enum field) throws FieldDecoratorException {
        if(field == null){
            return StringUtil.EMPTY;
        }
        if (enumerationType.isString()){
            return field.name();
        }
        return String.valueOf(field.ordinal());
    }

    public Enum fromString(String field) throws FieldDecoratorException {
        if(field == null  ||  field.trim().isEmpty()){
            return  null;
        }
        if (enumerationType.isString()){
            return Enum.valueOf(clazzEnum, field);
        }
        return (Enum) clazzEnum.getEnumConstants()[Integer.valueOf(field)];
    }

    /**
     * Should to return a array of Class.
     * The array returned represent the types paramters of constructor
     * @return
     */
    public static Class<?>[] getTypesConstructorExtended(){
        return new Class[]{EnumerationType.class, Class.class};
    }

    /**
     * Return the methods names in annotation that contains the values to call the constructor
     * @return
     */
    public static String[] getMethodContainsContstructorValues(){
        return new String[]{"enumType", "enumClass"};
    }
}
