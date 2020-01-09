package io.dfjx.common.utils.type;



public class ByteTypeHandler extends   BaseTypeHandler<Byte> {
    
    public ByteTypeHandler(Byte systemDefaultValue){
        super(systemDefaultValue);
    }

    @Override
    public Byte valueOf(Object object) {
        return Byte.valueOf(String.valueOf(object));
    }
}
