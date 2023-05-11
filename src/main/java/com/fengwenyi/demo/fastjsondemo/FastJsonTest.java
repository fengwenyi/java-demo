package com.fengwenyi.demo.fastjsondemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-06-15
 */
@Slf4j
public class FastJsonTest {

    @Test
    public void testTemplateConvert() {
        TemplateModel model = new TemplateModel();
        model.setName("张三");
        String content = "你好，NAME";
        content = model.convert(content);
        log.info(content);
    }

}
