package cn.by1e.co2.tests;

import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.SleepUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author bangquan.qian
 * @date 2020-07-15 16:08
 */
public class MyTest004 {

    private static volatile MyObject myObject;

    public static class MyObject {

        private boolean reuse;

        private String name;

        public MyObject(String name, boolean reuse) {
            this.name = name + StringUtils.SPACE;
            this.reuse = reuse;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            ConsoleUtils.sout(name + "do finalize");
            if (reuse) {
                myObject = this;
                ConsoleUtils.sout(name + "reuse");
            } else {
                ConsoleUtils.sout(name + "finalize done");
            }
        }
    }

    public static void main(String[] args) {
        MyObject myObject1 = new MyObject("myObject1", false);
        MyObject myObject2 = new MyObject("myObject2", true);

        ConsoleUtils.sout("main sleep 1");
        SleepUtils.sleepSeconds(3);
        ConsoleUtils.sout("main await 1");

        myObject1 = null;
        myObject2 = null;

        ConsoleUtils.sout("main sleep 2");
        SleepUtils.sleepSeconds(3);
        ConsoleUtils.sout("main await 2");

        System.gc();

        ConsoleUtils.sout("main sleep 3");
        SleepUtils.sleepSeconds(30);
        ConsoleUtils.sout("main await 3");

        System.gc();

        ConsoleUtils.sout("main sleep 3");
        SleepUtils.sleepSeconds(30);
        ConsoleUtils.sout("main await 3");

    }

}
