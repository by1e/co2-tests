package cn.by1e.co2.tests.unknown.demo001;

import cn.by1e.ox.core.util.ConsoleUtils;

/**
 * @author bangquan.qian
 * @date 2020-08-29 13:18
 */
public class Demo001 {

    static class Car {
        void drive() {
            ConsoleUtils.sout("car drive");
        }
    }


    static class BmwCar extends Car {
        @Override
        void drive() {
            ConsoleUtils.sout("bmw ar drive");
        }
    }

    static class AdiCar extends Car {
        @Override
        void drive() {
            ConsoleUtils.sout("adi ar drive");
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
        car.drive();
        car = new BmwCar();
        car.drive();
        car = new AdiCar();
        car.drive();
    }

}
