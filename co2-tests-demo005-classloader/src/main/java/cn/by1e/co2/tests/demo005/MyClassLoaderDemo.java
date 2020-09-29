package cn.by1e.co2.tests.demo005;

import cn.by1e.ox.core.util.ConsoleUtils;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author bangquan.qian
 * @date 2020-09-28 17:00
 */
public class MyClassLoaderDemo {

    public static void main(String[] args) throws Throwable {
        //test1();

        test2();

        //test3();

        //test4();
    }

    public static void test4() throws Throwable {
        ClassLoader mcl1 = new MyClassLoader();
        Class<?> clz1 = mcl1.loadClass("cn.by1e.co2.tests.demo005.Person");
        ConsoleUtils.sout(clz1);

        // byte-buddy
        ClassLoader mcl2 = new MyClassLoader4();
        Class<?> clz2 = mcl2.loadClass("cn.by1e.co2.tests.demo005.Person");
        ConsoleUtils.sout(clz2);

        ConsoleUtils.sout(ObjectUtils.equals(clz1, clz2));
    }

    public static void test3() throws Throwable {
        ClassLoader mcl1 = new MyClassLoader();
        Class<?> clz1 = mcl1.loadClass("cn.by1e.co2.tests.demo005.Person");
        ConsoleUtils.sout(clz1);

        // java-serialization
        ClassLoader mcl2 = new MyClassLoader3();
        Class<?> clz2 = mcl2.loadClass("cn.by1e.co2.tests.demo005.Person");
        ConsoleUtils.sout(clz2);

        ConsoleUtils.sout(ObjectUtils.equals(clz1, clz2));
    }

    public static void test2() throws Throwable {
        ClassLoader mcl1 = new MyClassLoader();
        Class<?> clz1 = mcl1.loadClass("cn.by1e.co2.tests.demo005.Person");
        ConsoleUtils.sout(clz1);

        // 自定义读取class文件
        ClassLoader mcl2 = new MyClassLoader2();
        Class<?> clz2 = mcl2.loadClass("cn.by1e.co2.tests.demo005.Person");
        ConsoleUtils.sout(clz2);

        ConsoleUtils.sout(ObjectUtils.equals(clz1, clz2));

        Person o1 = (Person) clz1.newInstance();
        Person o2 = (Person) clz2.newInstance();

        ClassLoader o1cl = o1.getClass().getClassLoader();
        ClassLoader o2cl = o2.getClass().getClassLoader();
        ConsoleUtils.sout(o1cl.getClass());
        ConsoleUtils.sout(o2cl.getClass());
    }

    public static void test1() throws Throwable {
        ClassLoader mcl1 = new MyClassLoader();
        Class<?> clz1 = mcl1.loadClass("cn.by1e.co2.tests.demo005.Person");
        ConsoleUtils.sout(clz1);

        ClassLoader mcl2 = new MyClassLoader();
        Class<?> clz2 = mcl2.loadClass("cn.by1e.co2.tests.demo005.Person");
        ConsoleUtils.sout(clz2);

        ConsoleUtils.sout(ObjectUtils.equals(clz1, clz2));
    }
}
