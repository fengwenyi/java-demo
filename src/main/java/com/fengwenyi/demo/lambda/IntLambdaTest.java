package com.fengwenyi.demo.lambda;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-08-29
 */
public class IntLambdaTest {

    public static void main(String[] args) {

        Demo demo1 = new Demo() {
            @Override
            public int convert(String s) {
                return Integer.parseInt(s);
            }
        };
        System.out.println(demo1.convert("111"));

        Demo demo2 = s -> {
                return Integer.parseInt(s);
            };
        System.out.println(demo2.convert("222"));

        Demo demo3 = s -> Integer.parseInt(s);
        System.out.println(demo3.convert("333"));

        Demo demo4 = Integer::parseInt;
        System.out.println(demo4.convert("444"));

        Demo demo5 = Integer::new;
        System.out.println(demo4.convert("555"));

    }

}

interface Demo {
    int convert(String s);
}
