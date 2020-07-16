package cn.by1e.co2.tests;

/**
 * @author bangquan.qian
 * @date 2020-07-15 16:08
 */
public class MyTest001 {

    private static class Test {

        private static volatile int a = 0;
        private static volatile int b = 0;

        public static void add() {
            for (int i = 0; i < 10000; i++) {
                a++;
                b++;
            }
        }

        public static void compare() {
            for (int i = 0; i < 1000; i++) {
                if (a < b) {
                    System.out.println("" + a + b + (a > b));
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Test.add();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Test.compare();
            }
        }).start();
    }
}
