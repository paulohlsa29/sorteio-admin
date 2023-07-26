package br.com.admin.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectUtil {
    
    public static void copy(Object source, Object dest) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        BeanInfo beanInfo = Introspector.getBeanInfo(source.getClass());
        PropertyDescriptor[] pList = beanInfo.getPropertyDescriptors();

        for(PropertyDescriptor pd : pList) {
            Method writer = null;
            Method reader = null;

            try {
                writer = pd.getWriteMethod();
                reader = pd.getReadMethod();
            } catch (Exception e) {}

            if(reader != null && writer != null && reader.invoke(source) != null)
                writer.invoke(dest, reader.invoke(source));
        }
        
    }
}
