package com.fengwenyi.demo.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-11-18
 */
public class CompletableFutureTest {

    @Test
    public void test() {
        try {
            System.out.println(execute());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private Object execute() throws Throwable {
        CompletableFuture<Object> resultFuture = new CompletableFuture<>();
        handleResponse(resultFuture);

        try {
            if (!resultFuture.isDone())
                throw new IllegalStateException("Response handling not done");

            return resultFuture.join();
        } catch (CompletionException e) {
            Throwable cause = e.getCause();
            if (cause != null)
                throw cause;
            throw e;
        }
    }

    private void handleResponse(CompletableFuture<Object> resultFuture) {
        String result = "success";
        resultFuture.complete(result);
    }

}
