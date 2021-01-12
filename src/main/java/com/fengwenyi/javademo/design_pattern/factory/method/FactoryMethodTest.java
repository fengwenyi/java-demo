package com.fengwenyi.javademo.design_pattern.factory.method;

import com.fengwenyi.javademo.design_pattern.factory.base.Phone;

/**
 * @author Erwin Feng
 * @since 2021-01-12
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        makeMiPhone();

        makeIPhone();
    }

    public static void makeMiPhone() {
        PhoneFactory phoneFactory = new MiPhoneFactory();
        Phone phone = phoneFactory.getPhone();
        if (phone != null) {
            phone.make();
        }
    }

    public static void makeIPhone() {
        PhoneFactory phoneFactory = new IPhoneFactory();
        Phone phone = phoneFactory.getPhone();
        if (phone != null) {
            phone.make();
        }
    }

}
