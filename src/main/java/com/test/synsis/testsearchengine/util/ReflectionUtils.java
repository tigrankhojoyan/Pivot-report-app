package com.test.synsis.testsearchengine.util;

import com.test.synsis.testsearchengine.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Reflection functions to work with objects at runtime
 */
@Slf4j
public class ReflectionUtils {

    /**
     * Invokes setter method of  {@param obj} object
     */
    public static void invokeSetter(Object obj, String propertyName, Object variableValue) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(propertyName, obj.getClass());
            Method setter = pd.getWriteMethod();
            setter.invoke(obj, variableValue);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException e) {
            log.error("Invalid property name", e);
            throw new BusinessException(e);
        }

    }

    /**
     * Invokes getter method of  {@param obj} object
     */
    public static Object invokeGetter(Object obj, String variableName) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(variableName, obj.getClass());
            Method getter = pd.getReadMethod();
            return getter.invoke(obj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException e) {
            log.error("Invalid property name", e);
            throw new BusinessException(e);
        }
    }
}
