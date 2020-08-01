package cn.by1e.co2.tests;

import cn.by1e.ox.core.util.ConsoleUtils;
import org.junit.Test;

import java.util.Objects;

/**
 * @author bangquan.qian
 * @date 2020-07-30 15:25
 */
public class MyTest008 {

    @Test
    public void test() {
        // 32 160
        String s1 = "update saas_goods set sn_goods_id = 659501 where goods_id = 3007302400;";
        String s2 = "update saas_goods set sn_goods_id = 659501 where goods_id = 3007302400;";
        ConsoleUtils.sout(Objects.equals(s1,s2));
    }

}
