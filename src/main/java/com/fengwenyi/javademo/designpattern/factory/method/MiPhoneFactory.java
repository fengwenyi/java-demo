package com.fengwenyi.javademo.designpattern.factory.method;

import com.fengwenyi.javademo.designpattern.factory.base.MiPhone;
import com.fengwenyi.javademo.designpattern.factory.base.Phone;

/**
 * @author Erwin Feng
 * @since 2021-01-12
 */
public class MiPhoneFactory implements PhoneFactory {
    @Override
    public Phone getPhone() {
        return new MiPhone();
    }
}
