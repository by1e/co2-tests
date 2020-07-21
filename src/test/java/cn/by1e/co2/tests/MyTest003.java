package cn.by1e.co2.tests;

import cn.by1e.ox.core.util.ConsoleUtils;

import java.math.BigDecimal;

/**
 * @author bangquan.qian
 * @date 2020-07-15 16:08
 */
public class MyTest003 {

    private static class Test {

        public void test() {
            double r1 = .1 + .2;
            float r2 = .2f + .2f;
            BigDecimal r3 = BigDecimal.valueOf(.1).add(BigDecimal.valueOf(.2));
            ConsoleUtils.sout(r1);
            ConsoleUtils.sout(r2);
            ConsoleUtils.sout(r3);
        }

    }

    public static void main(String[] args) {
        new Test().test();
    }

}
