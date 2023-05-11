package com.fengwenyi.demo.fordemo;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-11-12
 */
public class ForDemo {

    /**
     * for循环跳出
     */
    public static void forCycleJumpOut() {

        int i = 0, j = 0;

        jumpOut:
        for (; i < 10; i++) {
            for (; j < 10; j++) {
                if (j == 5) {
                    System.out.println("====>" + j);
                    break jumpOut;
                }
            }
        }
        System.out.printf("i=%d, j=%d%n", i, j);
    }

    public static void main(String[] args) {
        forCycleJumpOut();
    }

}
