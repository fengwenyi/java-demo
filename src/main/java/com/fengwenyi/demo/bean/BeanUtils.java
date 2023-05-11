package com.fengwenyi.demo.bean;

import com.fengwenyi.javalib.util.StringUtils;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-05-11
 */
public class BeanUtils {

    /* getter and setter 方法前缀 */
    private static final String [] GET_SET_PREFIX = { "get", "is", "set" };

    // 获取类对应的Lambda
    private static SerializedLambda getSerializedLambda(Serializable fn){
        SerializedLambda lambda = null;
        try{
            Method method = fn.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            lambda = (SerializedLambda) method.invoke(fn);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return lambda;
    }


    /**
     * getter方法接口定义
     */
    interface IGetter<T, R> extends Function<T, R>, Serializable {

    }

    /**
     * setter方法接口定义
     */
    interface ISetter<T, U> extends BiConsumer<T, U>, Serializable {

    }


    /**
     * 获取getter方法引用为属性名
     */
    public static <T> String getFieldNameByGet(IGetter<T, Object> fn) {
        SerializedLambda lambda = getSerializedLambda(fn);
        String methodName = lambda.getImplMethodName();
        return getFieldNameByMethodName(methodName);
    }

    /**
     * 获取setter方法引用为属性名
     */
    public static <T, U> String getFieldNameBySet(ISetter<T, U> fn) {
        SerializedLambda lambda = getSerializedLambda(fn);
        String methodName = lambda.getImplMethodName();
        return getFieldNameByMethodName(methodName);
    }

    private static String getFieldNameByMethodName(String methodName) {
        String prefix = getMethodPrefix(methodName);
        if(StringUtils.isEmpty(prefix)){
            System.err.println("无效的方法: " + methodName);
            return "";
        }
        return lowerCaseFirst(substringAfter(methodName, prefix));
    }

    private static String getMethodPrefix(String methodName) {
        for (String prefix : GET_SET_PREFIX) {
            if (methodName.startsWith(prefix)) {
                return prefix;
            }
        }
        return null;
    }

    // 截取前缀后的字符串
    private static String substringAfter(String content, String prefix) {
        return content.substring(prefix.length());
    }

    // 将字符串的第一个字符大写
    private static String lowerCaseFirst(String content) {
        String first = content.substring(0, 1);
        String after = content.substring(1);
        return first.toLowerCase() + after;
    }

}
