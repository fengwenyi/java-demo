package com.fengwenyi.javademo.ifdemo;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2022-01-27
 */
public class IfDemo {

    public static void main(String[] args) {
        String hospitalCode = "a";
        boolean result = itemNoCacheFilter(hospitalCode);
        System.out.println(hospitalCode + "-" + result); // true
        hospitalCode = "b";
        result = itemNoCacheFilter(hospitalCode);
        System.out.println(hospitalCode + "-" + result); // true
        hospitalCode = "c";
        result = itemNoCacheFilter(hospitalCode);
        System.out.println(hospitalCode + "-" + result); // false
    }

    private static boolean itemNoCacheFilter(String hospitalCode) {

        if ("a".equals(hospitalCode)) {
            return Boolean.TRUE;
        }
        if ("b".equals(hospitalCode)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
