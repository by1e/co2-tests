package cn.by1e.co2.tests.demo005;

/**
 * @author bangquan.qian
 * @date 2020-09-28 15:58
 */
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 最终会走到SystemClassLoader即Launcher.AppClassLoader
        return super.findClass(name);
    }

}
