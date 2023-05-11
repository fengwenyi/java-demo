package com.fengwenyi.demo.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fengwenyi.javalib.convert.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-06-28
 */
@Slf4j
public class JacksonTest {

    @Test
    public void testToJson() {
        User user = new User();
        user.setUsername("zs");
        user.setPassword("123456");
        user.setNickname("张三");
        user.setAge(18);
        String json = JsonUtils.convertString(user);
        log.info(json);
    }

    @Test
    public void testCovertXmlObject() {
        String xmlString = "<Response>\n\t<ResultCode>0</ResultCode>\\n\\t<ResultMsg>失败无数据</ResultMsg>\\n\\t<Items>\\n\\t</Items>\\n</Response>";
        xmlString = xmlString
                .replace("\n", "")
                .replace("\t", "")
                .replace("\\n", "")
                .replace("\\t", "")
        ;
        log.info(xmlString);
        XmlResponse<XmlItem> xmlResponse = covertXml(xmlString,
                new TypeReference<XmlResponse<XmlItem>>() {}
        );
        log.info(JsonUtils.convertString(xmlResponse));
    }

    public static <T> T covertXml(String content, TypeReference<T> valueTypeRef) {
        XmlMapper xmlMapper = new XmlMapper();
//        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //xmlMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
//        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        xmlMapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
//        xmlMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//        xmlMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        try {
            return xmlMapper.readValue(content, valueTypeRef);
        } catch (IOException e) {
            String errMsg = ExceptionUtils.getStackTrace(e);
            System.err.println(errMsg);
            return null;
        }
    }

    public static <T> T covertXml(String content, Class<?> collectionClass, Class<?>... elementClasses) {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        xmlMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            JavaType javaType = xmlMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
            return xmlMapper.readValue(content, javaType);
        } catch (IOException e) {
            String errMsg = ExceptionUtils.getStackTrace(e);
            System.err.println(errMsg);
            return null;
        }
    }

}
