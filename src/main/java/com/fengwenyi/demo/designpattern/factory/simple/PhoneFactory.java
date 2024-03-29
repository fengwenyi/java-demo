package com.fengwenyi.demo.designpattern.factory.simple;

import com.fengwenyi.demo.designpattern.factory.base.IPhone;
import com.fengwenyi.demo.designpattern.factory.base.MiPhone;
import com.fengwenyi.demo.designpattern.factory.base.Phone;

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
