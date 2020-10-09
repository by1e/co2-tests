package cn.by1e.co2.tests.demo002;

import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.TimeUtils;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author bangquan.qian
 * @date 2020-09-25 17:13
 */
public class RateLimiterDemo {

    public static void main(String[] args) throws Throwable {
        test1();
    }

    public static void test1() throws Throwable {
        new RateLimiterDemo1().test();
    }

    private static class RateLimiterDemo1 {

        public void test() throws Throwable {
            RateLimiter rateLimiter = RateLimiter.create(1);

            ExecutorService executorService = Executors.newFixedThreadPool(30);

            for (int idx = 0; idx < 30; idx++) {
                executorService.submit(new RateLimiterThread(rateLimiter));
            }

            executorService.awaitTermination(60, TimeUnit.SECONDS);

            executorService.shutdown();
        }

        private static class RateLimiterThread implements Callable<Void> {

            private RateLimiter rateLimiter;

            public RateLimiterThread(RateLimiter rateLimiter) {
                this.rateLimiter = rateLimiter;
            }

            @Override
            public Void call() {
                rateLimiter.acquire();

                ConsoleUtils.sout(Thread.currentThread().getName() + " acquire token，time = " + TimeUtils.now());

                return null;
            }

        }
    }

}
