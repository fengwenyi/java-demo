package com.fengwenyi.javademo.design_pattern.factory.abstract01;

import com.fengwenyi.javademo.design_pattern.factory.base.MiPhone;
import com.fengwenyi.javademo.design_pattern.factory.base.Phone;

/**
 * @author Erwin Feng
 * @since 2021-01-12
 */
public class MiPhoneFactory extends AbstractPhoneFactory{
    @Override
    protected Phone getPhone() {
        return new MiPhone();
    }
}
