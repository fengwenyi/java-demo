package com.fengwenyi.demo.designpattern.factory.abstract01;

/**
 * @author Erwin Feng
 * @since 2021-01-12
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {
        makeMiPhone();

        makeIPhone();
    }

    public static void makeMiPhone() {
        AbstractPhoneFactory phoneFactory = new MiPhoneFactory();
        phoneFactory.make();
    }

    public static void makeIPhone() {
        AbstractPhoneFactory phoneFactory = new IPhoneFactory();
        phoneFactory.make();
    }

}
