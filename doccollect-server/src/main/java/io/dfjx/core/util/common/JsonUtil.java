/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package io.dfjx.core.util.common;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


/**
 *
 * @author Administrator
 */
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();  
    //private static XmlMapper xmlMapper = new XmlMapper();  
    static {  
        objectMapper = createDefaultMapper();
    }
    
    public static ObjectMapper createDefaultMapper(){
    	
    	ObjectMapper om = new ObjectMapper();  
        //去掉默认的时间戳格式  
        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);  
        //设置为中国上海时区  
        om.setTimeZone(TimeZone.getTimeZone("GMT+8"));  
        om.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);  
        //空值不序列化  
        om.setSerializationInclusion(Include.NON_NULL);  
        //反序列化时，属性不存在的兼容处理  
        om.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);  
        //序列化时，日期的统一格式  
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));  
  
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);  
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);  
        //单引号处理  
        om.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        
        return om;
    }

   /*** 
    * 把object对象转化成为json对象 
    * @param object （map,collection,entity） 
    * @return string json 
    */ 
    public static String serialize(Object object) { 
        String strData = ""; 
        try {
             strData = objectMapper.writeValueAsString(object); 
        } catch (JsonProcessingException e) { 
             e.printStackTrace(); 
        }
        return strData;
    }
    
    public static String toJson(Object object, ObjectMapper om){
    	String strData = ""; 
        try {
             strData = om.writeValueAsString(object); 
        } catch (JsonProcessingException e) { 
             e.printStackTrace(); 
        }
        return strData;
    }
    
    public static String toJson(Object object){
        return serialize(object);
    }
    
    /*
    public static <T> Map<String,Object> json2map(String jsonStr)throws Exception{  
        return objectMapper.readValue(jsonStr, Map.class);  
    } 
    */
    /**
     * 将json转换为指定类型的对象
     * @param <T>
     * @param json
     * @param collectionClass
     * @param elementClasses
     * @return 
     */
    public static <T>T fromJson(String json,Class<?> collectionClass, Class<?>... elementClasses){
	try {
	    JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	    return objectMapper.readValue(json, javaType);
	} catch (IOException ex) {
	   ex.printStackTrace();
	}
	return null;
    }

}
