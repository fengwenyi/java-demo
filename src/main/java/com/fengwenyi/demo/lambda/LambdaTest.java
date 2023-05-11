package com.fengwenyi.demo.lambda;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-08-25
 */
public class LambdaTest {

    public void test1() {
        RequestExecutor requestExecutor = new Get();
        String result = requestExecutor.doExecute();
        System.out.println(result);
    }

    public void test2() {
        RequestExecutor requestExecutor = new RequestExecutor() {
            @Override
            public String doExecute() {
                return null;
            }
        };
        String result = requestExecutor.doExecute();
        System.out.println(result);
    }

    public void test3() {
        print(new RequestExecutor() {
            @Override
            public String doExecute() {
                return null;
            }
        });
    }

    public void test4() {
        print(() -> "");

        RequestExecutor executor = () -> null;
        print(executor);
    }

    public void print(RequestExecutor executor) {
        String result = executor.doExecute();
        System.out.println(result);
    }

}

@FunctionalInterface
interface RequestExecutor {

    String doExecute();

}

class Get implements RequestExecutor {

    @Override
    public String doExecute() {
        return "get request";
    }
}
