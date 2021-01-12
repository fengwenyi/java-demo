package com.fengwenyi.javademo.design_pattern.factory.method;

import com.fengwenyi.javademo.design_pattern.factory.base.MiPhone;
import com.fengwenyi.javademo.design_pattern.factory.base.Phone;

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
