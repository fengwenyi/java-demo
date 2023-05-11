package com.fengwenyi.demo.mapdemo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * map遍历
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-11-12
 */
public class MapTraverseDemo {

    private static final Map<Object, Object> init = new HashMap<>(); /*Map.of(
            "a", 1,
            "b", 2,
            "c", 3,
            "d", 4,
            "e", 5,
            "f", 6,
            "g", 7
    );*/

    static {
        init.put("", "");
    }

    private static void foreach1(Map<Object, Object> map) {
        for (Iterator<Map.Entry<Object, Object>> it = map.entrySet().iterator(); it.hasNext();) {
            Map.Entry<Object, Object> item = it.next();
            System.out.printf("key=%s, value=%s \n", item.getKey(), item.getValue());
        }
    }

    // 有效
    private static void foreach2(Map<Object, Object> map) {
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            System.out.printf("key=%s, value=%s \n", entry.getKey(), entry.getValue());
        }
    }

    // 有效
    private static void foreach3(Map<Object, Object> map) {
        map.forEach((key, value) -> {
            System.out.printf("key=%s, value=%s \n", key, value);
        }) ;
    }

    private static void foreach4(Map<Object, Object> map) {
        map.entrySet().stream().forEach(e -> {
            System.out.printf("key=%s, value=%s \n", e.getKey(), e.getValue());
        });
    }

    public static void main(String[] args) {
        Map<Object, Object> map = init;
        foreach1(map);
        System.out.println();
        foreach2(map);
        System.out.println();
        foreach3(map);
        System.out.println();
        foreach4(map);
    }

}
