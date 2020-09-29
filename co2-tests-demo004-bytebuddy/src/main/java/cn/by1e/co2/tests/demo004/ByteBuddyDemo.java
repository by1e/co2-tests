package cn.by1e.co2.tests.demo004;

import cn.by1e.ox.core.util.ConsoleUtils;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author bangquan.qian
 * @date 2020-09-28 11:31
 */
public class ByteBuddyDemo {

    public static class Person {

        @Override
        public String toString() {
            return super.toString();
        }

    }

    public static void test1() throws Throwable {
        Class<?> srcClz = Person.class;

        Class<?> dstClz = new ByteBuddy()
                .subclass(srcClz)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("hello world"))
                .make()
                .load(srcClz.getClassLoader())
                .getLoaded();

        ConsoleUtils.sout(dstClz.getName());

        Object o = dstClz.newInstance();

        ConsoleUtils.sout(o.toString());
    }

    public static void test2() throws Throwable {
        Class<?> srcClz = Person.class;

        ByteBuddy byteBuddy = new ByteBuddy();

        byteBuddy = byteBuddy.with(new NamingStrategy.SuffixingRandom("suffix"));

        DynamicType.Builder<?> dynamicTypeBuilder = byteBuddy
                .subclass(srcClz)
                .name("cn.world.hello.Person");

        DynamicType.Unloaded<?> dynamicTypeUnloaded = dynamicTypeBuilder.make();

        DynamicType.Loaded<?> dynamicTypeLoaded = dynamicTypeUnloaded.load(ClassLoader.getSystemClassLoader(),
                ClassLoadingStrategy.Default.CHILD_FIRST);

        Class<?> dstClz = dynamicTypeLoaded.getLoaded();

        ConsoleUtils.sout(dstClz.getName());
    }

    public static void main(String[] args) throws Throwable {
        //test1();

        test2();

    }

}
