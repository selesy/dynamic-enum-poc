package com.selesy.poc.dynamicenumpoc;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class DynamicEnumFactory<T extends Enum<T>> {
  
  public void something(Class<T> clazz, Class<?>[] parameterTypes, Object[] parameterValues) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
    T[] oldValues = (T[]) ReflectionUtilities.getStaticFieldValue(clazz, "ENUM$VALUES");
    T[] newValues = Arrays.copyOf(oldValues, oldValues.length + 1);
    
    System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
    
    T newValue = (T) ReflectionUtilities.invokeEnumConstructor(clazz, parameterTypes, parameterValues);
    
    newValues[newValues.length - 1] = newValue;
    
    ReflectionUtilities.setStaticFieldValue(clazz, "ENUM$VALUES", newValues);
  }

}
