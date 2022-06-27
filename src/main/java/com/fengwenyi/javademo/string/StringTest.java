package com.fengwenyi.javademo.string;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2022-04-25
 */
@Slf4j
public class StringTest {

    @Test
    public void substring() {
        String str = "test-" + System.currentTimeMillis();
        int i = str.lastIndexOf("-");
        if (i > -1) {
            str = str.substring(0, i);
            log.info("str: [{}]", str);
            Assert.assertEquals("test", str);
        }
    }

    @Test
    public void testStringTemplateRepl() {

    }

    private void s(String content, Map<String, Object> map) {
        String template = "";
        // template.replaceAll("/{{(.*?)}}/g", )
    }

    private void testPattern() {
        String p = "/{{(.*?)}}/g";
        Pattern pattern = Pattern.compile(p);
        String content = "";
        Matcher matcher = pattern.matcher(content);
        matcher.matches();
    }

}
