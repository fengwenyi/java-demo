package com.fengwenyi.demo.string;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.HashMap;
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
    public void testStringTemplateByPattern() {
//        String pattern = "/{{(.*?)}}/g";
//        String smsTemplate = "验证码:{{code}}，您正在登录管理后台，5分钟内输入有效。";
//        boolean matches = Pattern.matches(pattern, smsTemplate);
//        log.info("[{}]", matches);
    }

    @Test
    public void testStringTemplateBySpring() {
        String smsTemplate = "验证码:#{[code]}，您正在登录管理后台，5分钟内输入有效。";
        // String smsTemplate = "验证码:${[code]}，您正在登录管理后台，5分钟内输入有效。";
        Map<String, Object> params = new HashMap<>();
        params.put("code", 1234);

        ExpressionParser parser = new SpelExpressionParser();
        TemplateParserContext parserContext = new TemplateParserContext();
        String content = parser.parseExpression(smsTemplate, parserContext).getValue(params, String.class);

        System.out.println(content);
    }

    @Test
    public void testStringTemplateByApache() {
        Map<String, Object> params = new HashMap<>();
        params.put("code", 1234);
        String smsTemplate = "验证码:${code}，您正在登录管理后台，5分钟内输入有效。";
        StringSubstitutor sub = new StringSubstitutor(params);
        String content= sub.replace(smsTemplate);
        System.out.println(content);
    }

}
