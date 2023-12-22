package com.fengwenyi.demo.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2023-12-22
 */
public class ThreadNotifyAllDemo {

    public static void main(String[] args) throws InterruptedException {
        TaskQueue q = new TaskQueue();
        List<Thread> ts = new ArrayList<Thread>();
        for (int i=0; i<5; i++) {
            Thread t = new Thread() {
                public void run() {
                    // 执行task:
                    while (true) {
                        try {
                            String thread = Thread.currentThread().getName();
                            System.out.println("thread: " + thread);
                            String s = q.getTask();
                            System.out.println("execute task: " + s + ", thread: " + thread);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }

                    /*try {
                        String thread = Thread.currentThread().getName();
                        System.out.println("thread: " + thread);
                        String s = q.getTask();
                        System.out.println("execute task: " + s + ", thread: " + thread);
                        Thread.sleep(10);
                        System.out.println("thread: " + thread + ", 执行完毕");
                    } catch (InterruptedException e) {
                        return;
                    }*/

                }
            };
            t.start();
            ts.add(t);
        }
        Thread add = new Thread(() -> {
            for (int i=0; i<10; i++) {
                // 放入task:
                String s = "t-" + Math.random();
                System.out.println("add task: " + s);
                q.addTask(s);
                try {
                    Thread.sleep(100);
                } catch(InterruptedException e) {

                }
            }
        });
        add.start();
        add.join();
        Thread.sleep(100);
        for (Thread t : ts) {
            t.interrupt();
        }
    }
}

class TaskQueue {
    Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String s) {
        this.queue.add(s);
         this.notifyAll();
    }

    public synchronized String getTask() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }
        return queue.remove();
    }
}
