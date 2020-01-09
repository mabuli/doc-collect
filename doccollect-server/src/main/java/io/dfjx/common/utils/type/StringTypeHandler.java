package io.dfjx.common.utils.type;

public class StringTypeHandler extends   BaseTypeHandler<String> {

    @Override
    public String getSystemDefaultValue() {
        return "";
    }

    @Override
    public String valueOf(Object object) {
        return String.valueOf(object);
    }


  
}
