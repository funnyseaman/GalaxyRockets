package com.WRan.util;


import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;


public class ReflectUtil {
    public static <T> String getClassName(T t) {
        String []tableNames = t.toString().split("\\s+");
        if(tableNames.length>1){
            return tableNames[1];
        }else{
            return tableNames[0];
        }

    }

    public static <T> Field[] getClassFields(T t) {
        Class c = t.getClass();
        return c.getFields();
    }

    public static <T> Field getFieldByName(T t, String name) throws NoSuchFieldException {
        Class c = t.getClass();
        return c.getField(name);
    }

    public static <T> List<Field> getNotNullFields(T t) throws IllegalAccessException {
        Class c = t.getClass();
        LinkedList<Field> fs = new LinkedList<>();
        for (Field f : c.getFields()) {
            if (f.get(t) != null) {
                fs.add(f);
            }
        }
        return fs;
    }
}
