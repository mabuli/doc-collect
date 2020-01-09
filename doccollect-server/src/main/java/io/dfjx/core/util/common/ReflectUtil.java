package io.dfjx.core.util.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import io.dfjx.common.utils.type.TypeUtil;

public class ReflectUtil {
    /**
     * 通过反射，获得定义Class时声明的父类的第一个范型参数的类型。
     */
    public static Class getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射，获得定义Class时声明的父类的范型参数的类型。
     * 如没有找到符合要求的范型参数，则递归向上直到Object。
     *
     * @param clazz 要进行查询的类
     * @param index 如有多个范型声明该索引从0开始
     * @return 在index位置的范型参数的类型，如果无法判断则返回<code>Object.class</code>
     */
    public static Class getSuperClassGenricType(Class clazz, int index) {

        boolean flag = true;
        Type genType = clazz.getGenericSuperclass();
        Type[] params = null;

        if (!(genType instanceof ParameterizedType))
            flag = false;
        else {
            params = ((ParameterizedType) genType).getActualTypeArguments();
            if (index >= params.length || index < 0)
                flag = false;
            if (!(params[index] instanceof Class))
                flag = false;
        }
        if (!flag) {
            clazz = clazz.getSuperclass();
            if (clazz == Object.class)
                return Object.class;
            else
                return getSuperClassGenricType(clazz, index);
        }

        return (Class) params[index];
    }

    /**
     * 将javaBean转换成Map
     * @param javaBean javaBean
     * @return Map对象
     */
     public static Map toMap(Object javaBean) {
        Map result = new HashMap();
        Method[] methods = javaBean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try {
                if (method.getName().startsWith("get")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    Object value = method.invoke(javaBean, (Object[])null);
                    result.put(field, null == value ? "" : value.toString());
                }
            }
            catch (Exception e)
            {
            }
        }
        return result;
    }
     
     /**
      * 将map转换成Javabean
      *
      * @param javabean javaBean
      * @param data map数据
      */
      public static Object toObject(Map data, Object javabean) {
    	  return toObjectByField(data, javabean);
      }
  /**
   * 将map转换成Javabean
   *
   * @param javabean javaBean
   * @param data map数据
   */
   public static Object toObjectByField(Map data, Object javabean) {
 	  Field[] fs = javabean.getClass().getDeclaredFields();
       for (Field f : fs) {
           try {
         	  f.setAccessible(true); //设置些属性是可以访问的
         	  String key = f.getName().toLowerCase();
         	  Class typeClass = f.getType();
         	  Boolean b = false;
         	  String mkey = "";
         	  Iterator<String> iter = data.keySet().iterator();
         	  while(iter.hasNext()){  
         		 String str = iter.next();
         		 if(key.equals(str.toLowerCase())){
         			 b = true;
         			 mkey = str;
         			 break;
         		 }
              }
         	  if(b)
         		  f.set(javabean, TypeUtil.parseObject(typeClass, data.get(mkey).toString()));
           } catch (Exception e) {
           }
       }
       return javabean;
   }
   /**
    * 将map转换成Javabean(使用驼峰命名)
    *
    * @param javabean javaBean
    * @param data map数据
    */
    public static Object toObjectByMethod1(Map data, Object javabean) {
        Method[] methods = javabean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try {
                if (method.getName().startsWith("set")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("set") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    method.invoke(javabean, new Object[] {
                        data.get(field)
                    });
                }
            } catch (Exception e) {
            }
        }
        return javabean;
    }
    /**
     * 将map转换成Javabean(使用全大写命名)
     *
     * @param javabean javaBean
     * @param data map数据
     */
     public static Object toObjectByMethod2(Map data, Object javabean) {
         Method[] methods = javabean.getClass().getDeclaredMethods();
         for (Method method : methods) {
             try {
                 if (method.getName().startsWith("set")) {
                     String field = method.getName();
                     field = field.substring(field.indexOf("set") + 3);
                     method.invoke(javabean, new Object[] {
                         data.get(field)
                     });
                 }
             } catch (Exception e) {
             }
         }
         return javabean;
     }
    public static Object toObjectByClass(Map data, Class<?> cls) {
        try{
            Object obj = cls.newInstance();
            return toObject(data, obj);
        }catch(Exception ex) {
            return null;
        }
    }
    
    
    /**
     * 获取属性值
     * @param model
     * @param fieldName
     * @return 
     */
    public static Object getValue(Object model, String fieldName) {
        String name = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1); // 将属性的首字符大写
        try {
            Object args[] = null;
            Method m = model.getClass().getMethod("get" + name);
            if(m != null)
                return m.invoke(model, args);
            return "";
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.printf("无法调用"+model.getClass().getName()+"的get" + name +"方法");
            return "";
        }
    }
    /**
     * 获取属性值
     * @param model
     * @param fieldName
     * @return 
     */
    public static <V> V getValue(V model, String fieldName, Class cls) {
        String name = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1); // 将属性的首字符大写
        try {
            Object args[] = null;
            Method m = cls.getMethod("get" + name);
            if(m != null) {
                Object val = m.invoke(model, args);
                if(val == null)
                	return null;
                return (V)val;
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.printf("无法调用"+model.getClass().getName()+"的get" + name +"方法");
            return null;
        }
    }
    /**
     * 设置属性值
     * @param model
     * @param fieldName 
     * @param value
     * @param cls
     * @param valCls
     */
    public static <T> void setValue(T model, String fieldName, Object value, Class cls, Class valCls) {
        String name = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1); // 将属性的首字符大写
        try {
            Object args[] = new Object[]{value};
            Method m = cls.getMethod("set" + name, valCls);
            if(m == null)
            	return;
            m.invoke(model, args);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.printf("无法调用"+model.getClass().getName()+"的set" + name +"方法");
        }
    }

    public static Method getMethod(Object model, String methodName, Object...params){
    	Class clazz = model.getClass();
    	Method[] methods = clazz.getMethods();
    	Method fn = null;
    	//Method lastfn = null; //未比较参数个数,有缺陷
    	for(Method method:methods){
            if(method.getName().equals(methodName)){
            	fn = method;
            	//fn.setAccessible(true);
                break;
            }
        }
    	return fn;
    }
    
    public static Object invoke(Object model, String methodName, Object...params){
    	Method fn = getMethod(model, methodName, params);
    	if(fn == null)
    		return null;
        try {
			return fn.invoke(model ,params);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
    }
}
