package io.dfjx.common.utils.type;

public class DoubleTypeHandler extends   BaseTypeHandler<Double> {
    
    public DoubleTypeHandler(Double systemDefaultValue){
        super(systemDefaultValue);
    }

    @Override
    public Double valueOf(Object object) {
    	if(object == null || "".equals(object))
    		return 0d;
        return Double.valueOf(String.valueOf(object));
    }
}
