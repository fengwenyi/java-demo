package com.fengwenyi.demo.designpattern.factory.abstract01;

import com.fengwenyi.demo.designpattern.factory.base.IPhone;
import com.fengwenyi.demo.designpattern.factory.base.Phone;

/**
 * @author Erwin Feng
 * @since 2021-01-12
 */
public class IPhoneFactory extends AbstractPhoneFactory {
    @Override
    protected Phone getPhone() {
        return new IPhone();
    }
}
