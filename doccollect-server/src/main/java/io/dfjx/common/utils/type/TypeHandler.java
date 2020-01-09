package io.dfjx.common.utils.type;

public interface TypeHandler<T> {
  public T parse(Object object, T defaultValue)  throws TypeException;
  public T parse(Object object)  throws TypeException;
}
