package xtc.field.value;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class UtilSplit {

	/**
	 * 根据属性名获取属性值
	 * */
    public Object getFieldValueByName(String fieldName, Object o) {
        try {  
            String firstLetter = fieldName.substring(0, 1).toUpperCase();  
            String getter = "get" + firstLetter + fieldName.substring(1);  
            Method method = o.getClass().getMethod(getter, new Class[] {});  
            Object value = method.invoke(o, new Object[] {});  
            return value;  
        } catch (Exception e) {  
            //log.error(e.getMessage(),e);  
            return null;  
        }  
    } 
    
    /**
     * 获取属性名数组
     * */
    public String[] getFiledName(Object o){
    	Field[] fields=o.getClass().getDeclaredFields();
       	String[] fieldNames=new String[fields.length];
    	for(int i=0;i<fields.length;i++){
    		//System.out.println(fields[i].getType());
    		fieldNames[i]=fields[i].getName();
    	}
    	return fieldNames;
    }
    
    
    /**
     * 获取对象的所有属性值，返回一个对象数组
     * */
    public Object[] getFiledValues(Object o){
    	String[] fieldNames=this.getFiledName(o);
    	Object[] value=new Object[fieldNames.length];
    	for(int i=0;i<fieldNames.length;i++){
    		value[i]=this.getFieldValueByName(fieldNames[i], o);
    	}
    	return value;
    }	
	
}
