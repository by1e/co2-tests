package cn.by1e.co2.tests;

import cn.by1e.ox.core.util.ConsoleUtils;
import cn.by1e.ox.core.util.InvokeUtils;
import cn.by1e.ox.core.util.JsonUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author bangquan.qian
 * @date 2020-07-29 17:31
 * @see Map#compute(Object, BiFunction)
 * @see Map#computeIfAbsent(Object, Function)
 * @see Map#computeIfPresent(Object, BiFunction)
 * @see Map#merge(Object, Object, BiFunction)
 */
public class MyTest006 {

    private static class Student {
        private String name;
        private String sex;
        private Integer age;

        public Student(String name, String sex, Integer age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public String getSex() {
            return sex;
        }

        public Integer getAge() {
            return age;
        }
    }

    /**
     * 把 list 中的对象，按照属性男女分组，然后把年龄汇总
     */
    @Test
    public void test1() {
        //学生的集合
        List<Student> students = genStudents();

        //声明接收结果的 map  
        Map<String, AtomicInteger> resultMap1 = new TreeMap<>();
        for (Student student : students) {
            AtomicInteger age = resultMap1.get(student.getSex());
            if (age == null) {
                age = new AtomicInteger();
            }
            resultMap1.put(student.getSex(), InvokeUtils.unaryInvoke2(age, e -> e.addAndGet(student.getAge())));
        }

        Map<String, AtomicInteger> resultMap2 = new TreeMap<>();
        for (Student student : students) {
            resultMap2.merge(student.getSex(), new AtomicInteger(student.getAge()), (a, b) -> InvokeUtils.unaryInvoke2(a, e -> e.addAndGet(b.get())));
        }

        ConsoleUtils.prettyJson(resultMap1);
        ConsoleUtils.prettyJson(resultMap2);
        ConsoleUtils.sout(ObjectUtils.equals(JsonUtils.toJsonString(resultMap1), JsonUtils.toJsonString(resultMap2)));
    }

    private List<Student> genStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("张三", "男", 18));
        students.add(new Student("李四", "男", 20));
        students.add(new Student("韩梅梅", "女", 18));
        students.add(new Student("小红", "女", 45));
        return students;
    }

    /**
     * 按照男女分组
     */
    @Test
    public void test2() {
        List<Student> students = genStudents();

        Map<String, List<Student>> resultMap1 = new TreeMap<>();
        for (Student student : students) {
            List<Student> list = resultMap1.get(student.getSex());
            if (list == null) {
                resultMap1.put(student.getSex(), list = new ArrayList<>());
            }
            list.add(student);
        }

        Map<String, List<Student>> resultMap2 = new TreeMap<>();
        for (Student student : students) {
            List<Student> list = resultMap2.computeIfAbsent(student.getSex(), e -> new ArrayList<>());
            list.add(student);
        }

        ConsoleUtils.prettyJson(resultMap1);
        ConsoleUtils.prettyJson(resultMap2);
        ConsoleUtils.sout(ObjectUtils.equals(JsonUtils.toJsonString(resultMap1), JsonUtils.toJsonString(resultMap2)));
    }

    /**
     * 统计字符串中每一个的 单词出现的次数
     */
    @Test
    public void test3() {
        String s =
                "Lorem ipsum dolor sit amet consetetur iam nonumy sadipscing " +
                        " elitr, sed diam nonumy eirmod tempor invidunt ut erat sed " +
                        "labore et dolore magna dolor sit amet aliquyam erat sed diam";

        Map<String, AtomicInteger> resultMap1 = new TreeMap<>();
        for (String t : s.split(" ")) {
            AtomicInteger counts = resultMap1.get(t);
            if (counts == null) {
                resultMap1.put(t, counts = new AtomicInteger());
            }
            counts.incrementAndGet();
        }

        Map<String, AtomicInteger> resultMap2 = new TreeMap<>();
        for (String t : s.split(" ")) {
            resultMap2.compute(t, (k, v) -> {
                if (v == null) {
                    v = new AtomicInteger();
                }
                v.incrementAndGet();
                return v;
            });
        }

        ConsoleUtils.prettyJson(resultMap1);
        ConsoleUtils.prettyJson(resultMap2);
        ConsoleUtils.sout(ObjectUtils.equals(JsonUtils.toJsonString(resultMap1), JsonUtils.toJsonString(resultMap2)));
    }

    /**
     * 统计字符串中指定的 单词出现的次数
     */
    @Test
    public void test4() {
        String s =
                "Lorem ipsum dolor sit amet consetetur iam nonumy sadipscing " +
                        " elitr, sed diam nonumy eirmod tempor invidunt ut erat sed " +
                        "labore et dolore magna dolor sit amet aliquyam erat sed diam";

        Map<String, AtomicInteger> resultMap1 = new TreeMap<>();
        resultMap1.put("sed", new AtomicInteger(0));
        for (String t : s.split(" ")) {
            AtomicInteger counts = resultMap1.get(t);
            if (counts == null) {
                continue;
            }
            counts.incrementAndGet();
        }

        Map<String, AtomicInteger> resultMap2 = new TreeMap<>();
        resultMap2.put("sed", new AtomicInteger(0));
        for (String t : s.split(" ")) {
            resultMap2.computeIfPresent(t, (k, v) -> InvokeUtils.unaryInvoke2(v, AtomicInteger::incrementAndGet));
        }

        ConsoleUtils.prettyJson(resultMap1);
        ConsoleUtils.prettyJson(resultMap2);
        ConsoleUtils.sout(ObjectUtils.equals(JsonUtils.toJsonString(resultMap1), JsonUtils.toJsonString(resultMap2)));
    }

}
