package cn.by1e.co2.tests;

import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.InvokeUtils;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @see HashMap#hash(java.lang.Object)
 *
 * @see HashMap#tableSizeFor(int)
 *
 * @see java.util.HashMap#resize()
 *
 * @see java.util.HashMap#putVal(int, java.lang.Object, java.lang.Object, boolean, boolean)
 *
 * @see java.util.HashMap#treeifyBin(java.util.HashMap.Node[], int)
 *
 * @see java.util.HashMap#removeNode(int, java.lang.Object, java.lang.Object, boolean, boolean)
 *
 * @author bangquan.qian
 * @date 2020-07-28 17:48
 */
public class MyTest005 {

    public static class Test {
        private static LinkedList<Object> lock = new LinkedList<>();

        public synchronized void pop() {
            ConsoleUtils.sout("pop start");
            while (lock.peek() == null) {
                notifyAll();
            }
            lock.pop();
            ConsoleUtils.sout("pop done");
        }

        public synchronized void push() throws InterruptedException {
            ConsoleUtils.sout("push start");
            synchronized (lock) {
                ConsoleUtils.sout("push locked");
                while (lock.isEmpty()) {
                    lock.push(new Object());
                }
                wait();
                ConsoleUtils.sout("push unlocked");
            }
            ConsoleUtils.sout("push done");
        }
    }

    public static void main(String[] args) {
        Test test = new Test();
        new Thread(() -> {
            while (true) {
                InvokeUtils.voidInvokeRe(test::push);
                ConsoleUtils.sout("push");
            }
        }).start();
        new Thread(() -> {
            while (true) {
                InvokeUtils.voidInvokeRe(test::pop);
                ConsoleUtils.sout("pop");
            }
        }).start();
    }

}
