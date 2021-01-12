package com.fengwenyi.javademo.design_pattern.factory.simple;

import com.fengwenyi.javademo.design_pattern.factory.base.Phone;

/**
 * @author Erwin Feng
 * @since 2021-01-12
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {
        makeMiPhone();

        makeIPhone();
    }

    public static void makeMiPhone() {
        PhoneFactory phoneFactory = new PhoneFactory();
        Phone phone = phoneFactory.getPhone("miPhone");
        if (phone != null) {
            phone.make();
        }
    }

    public static void makeIPhone() {
        PhoneFactory phoneFactory = new PhoneFactory();
        Phone phone = phoneFactory.getPhone("iPhone");
        if (phone != null) {
            phone.make();
        }
    }

}
