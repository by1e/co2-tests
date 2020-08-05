package cn.by1e.co2.tests;

import cn.by1e.ox.core.util.ConsoleUtils;
import org.junit.Test;

import java.util.Random;

/**
 * @author bangquan.qian
 * @date 2020-08-05 11:18
 */
public class MyTest011 {

    @Test
    public void test() {
        // 量子猴排
        int[] arr = new int[]{1, 3, 2, 5, 4, 7, 9, 6, 8, 0};
        long times = 0;
        while (!isOrder(arr)) {
            shuffle(arr);
            times++;
        }
        ConsoleUtils.sout(times);
        ConsoleUtils.json(arr);
    }

    private void shuffle(int[] arr) {
        int len = arr.length;
        Random random = new Random();
        for (int idx = 0; idx < len; idx++) {
            int jdx = random.nextInt(len);
            swap(arr, idx, jdx);
        }
    }

    private void swap(int[] arr, int idx, int jdx) {
        int tmp = arr[idx];
        arr[idx] = arr[jdx];
        arr[jdx] = tmp;
    }

    private boolean isOrder(int[] arr) {
        int len = arr.length;
        for (int idx = 1; idx < len; idx++) {
            if (arr[idx] < arr[idx - 1]) {
                return false;
            }
        }
        return true;
    }

}
