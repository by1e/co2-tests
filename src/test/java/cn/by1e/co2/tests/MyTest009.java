package cn.by1e.co2.tests;

import cn.by1e.ox.core.util.ConsoleUtils;
import org.junit.Test;

/**
 * @author bangquan.qian
 * @date 2020-07-31 11:30
 */
public class MyTest009 {

    @Test
    public void test() {
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        String s4 = new String("abc");
        String s5 = new String(new char[]{'a', 'b', 'c'});

        ConsoleUtils.sout(s1 == s2);
        ConsoleUtils.sout(s1.equals(s2));
        ConsoleUtils.sout(s1 == s3);
        ConsoleUtils.sout(s1.equals(s3));
        ConsoleUtils.sout(s3 == s4);
        ConsoleUtils.sout(s3.equals(s4));
        ConsoleUtils.sout(s3 == s5);
        ConsoleUtils.sout(s3.equals(s5));
    }

}
