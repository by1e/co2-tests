package cn.by1e.co2.tests.demo005;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author bangquan.qian
 * @date 2020-09-28 15:58
 */
@Slf4j
public class MyClassLoader2 extends MyClassLoader {

    private static final String PATH = "/Users/chainz/Workspace/Personal/by1e/co2-tests/co2-tests-demo005-classloader/target/classes/";

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        //return super.loadClass(name, resolve);

        // 破坏双亲委托机制，除了BootstrapClassLoader，其他直接findClass，不走App&Ext
        Class<?> clz = null;

        try {
            Method findBootstrapClassOrNull = ClassLoader.class.getDeclaredMethod("findBootstrapClassOrNull", String.class);
            findBootstrapClassOrNull.setAccessible(true);
            clz = (Class<?>) findBootstrapClassOrNull.invoke(this, name);
        } catch (Throwable e) {
            log.error("findBootstrapClassOrNull", e);
        }

        if (clz == null) {
            clz = findClass(name);
        }

        if (resolve) {
            super.resolveClass(clz);
        }

        return clz;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //return super.findClass(name);

        String path = PATH + name.replace(".", "/") + ".class";

        byte[] bytes = null;
        try {
            bytes = FileUtils.readFileToByteArray(new File(path));
        } catch (IOException e) {
            log.error("readFileToByteArray", e);
        }
        if (ArrayUtils.isEmpty(bytes)) {
            throw new ClassNotFoundException(name);
        }

        return super.defineClass(name, bytes, 0, bytes.length);
    }

}
