package com.fengwenyi.javademo.design_pattern.factory.simple;

import com.fengwenyi.javademo.design_pattern.factory.base.IPhone;
import com.fengwenyi.javademo.design_pattern.factory.base.MiPhone;
import com.fengwenyi.javademo.design_pattern.factory.base.Phone;

/**
 * @author Erwin Feng
 * @since 2021-01-12
 */
public class PhoneFactory {

    public Phone getPhone(String phoneType) {
        if (phoneType.equals("miPhone")) {
            return new MiPhone();
        } else if (phoneType.equals("iPhone")) {
            return new IPhone();
        } else {
            return null;
        }
    }

}
