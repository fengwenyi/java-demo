package com.fengwenyi.javademo.design_pattern.factory.abstract01;

import com.fengwenyi.javademo.design_pattern.factory.base.Phone;

/**
 * @author Erwin Feng
 * @since 2021-01-12
 */
public abstract class AbstractPhoneFactory {

    public void make() {
        Phone phone = getPhone();
        if (phone != null) {
            phone.make();
        }
    }

    protected abstract Phone getPhone();

}
