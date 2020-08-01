package cn.by1e.co2.tests;

import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.InvokeUtils;
import org.junit.Test;

import java.util.function.BiFunction;

/**
 * @author bangquan.qian
 * @date 2020-07-29 18:15
 */
public class MyTest007 {

    // f(x)=(a+b*c)^d
    @Test
    public void test2() {
        int val1 = fx(1, 2, 3, 4);
        int val2 = (int) Math.pow(1 + 2 * 3, 4);
        ConsoleUtils.sout(val1);
        ConsoleUtils.sout(val2);
    }

    private static int fx(int a, int b, int c, int d) {
        return fun(
                fun(a,
                        fun(b, c, (x, y) -> x * y),
                        (x, y) -> x + y
                ),
                d,
                (x, y) -> (int) Math.pow(x, y)
        );
    }


    private static <R, X, Y> R fun(X x, Y y, BiFunction<X, Y, R> function) {
        return function.apply(x, y);
    }

    @Test
    public void test1() {
        int num = InvokeUtils.unaryInvoke(1,
                x -> InvokeUtils.unaryInvoke(x,
                        a -> InvokeUtils.unaryInvoke(a,
                                b -> InvokeUtils.unaryInvoke(b,
                                        c -> InvokeUtils.unaryInvoke(c,
                                                d -> InvokeUtils.unaryInvoke(d, e -> e + 1)
                                        )
                                )
                        )
                )
        );
        ConsoleUtils.sout(num);
    }
}
