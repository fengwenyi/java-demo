package com.fengwenyi.javademo.design_pattern.factory.abstract01;

import com.fengwenyi.javademo.design_pattern.factory.base.IPhone;
import com.fengwenyi.javademo.design_pattern.factory.base.Phone;

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
