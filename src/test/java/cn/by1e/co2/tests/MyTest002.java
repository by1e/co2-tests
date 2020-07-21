package cn.by1e.co2.tests;

import cn.by1e.ox.core.util.ConsoleUtils;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author bangquan.qian
 * @date 2020-07-15 16:08
 */
public class MyTest002 {

    private static class Test {

        private static final int MAX = 0x0000_ffff;

        public void test() {

            Random random = new Random();

            Set<Integer> hashSets = new HashSet<>(MAX);
            BloomFilter<Integer> bloomFilter = BloomFilter.<Integer>create(Funnels.integerFunnel(), MAX);

            int error = 0;
            for (int i = 0; i < MAX; i++) {
                Integer num = Integer.valueOf(random.nextInt());
                if (hashSets.contains(num) != bloomFilter.mightContain(num)) {
                    error++;
                }
                hashSets.add(num);
                bloomFilter.put(num);
            }

            ConsoleUtils.sout((double) error / MAX);
        }

    }

    public static void main(String[] args) {
        new Test().test();
    }
}
