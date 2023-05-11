package com.fengwenyi.demo.designpattern.factory.method;

import com.fengwenyi.demo.designpattern.factory.base.IPhone;
import com.fengwenyi.demo.designpattern.factory.base.Phone;

/**
 * @author Erwin Feng
 * @since 2021-01-12
 */
public class IPhoneFactory implements PhoneFactory {
    @Override
    public Phone getPhone() {
        return new IPhone();
    }
}
