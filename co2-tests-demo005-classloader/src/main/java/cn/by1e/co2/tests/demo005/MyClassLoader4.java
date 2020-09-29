package cn.by1e.co2.tests.demo005;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;

/**
 * @author bangquan.qian
 * @date 2020-09-28 15:58
 */
@Slf4j
public class MyClassLoader4 extends MyClassLoader2 {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //return super.findClass(name);

        //Thread.currentThread().getContextClassLoader()是可以被修改的
        //ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        //ClassLoader.getSystemClassLoader()获取的是Launcher.AppClassLoader
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        Class<?> srcClz = classLoader.loadClass(name);

        ByteBuddy byteBuddy = new ByteBuddy();

        DynamicType.Unloaded<?> dynamicTypeUnloaded = byteBuddy
                .subclass(srcClz)
                .name(name)
                .make();

        byte[] bytes = dynamicTypeUnloaded.getBytes();

        return super.defineClass(name, bytes, 0, bytes.length);
    }

}
