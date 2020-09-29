package cn.by1e.co2.tests.demo005;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/**
 * @author bangquan.qian
 * @date 2020-09-28 15:58
 */
@Slf4j
public class MyClassLoader3 extends MyClassLoader2 {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //return super.findClass(name);

        //Thread.currentThread().getContextClassLoader()是可以被修改的
        //ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        //ClassLoader.getSystemClassLoader()获取的是Launcher.AppClassLoader
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        Class<?> srcClz = classLoader.loadClass(name);

        byte[] bytes = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(srcClz);
            bytes = baos.toByteArray();
        } catch (Throwable e) {
            log.error("writeObject", e);
        }
        if (ArrayUtils.isEmpty(bytes)) {
            throw new ClassNotFoundException(name);
        }

        return super.defineClass(name, bytes, 0, bytes.length);
    }

}
