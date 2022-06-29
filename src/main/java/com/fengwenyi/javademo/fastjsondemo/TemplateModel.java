package com.fengwenyi.javademo.fastjsondemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.annotation.JSONField;
import com.fengwenyi.javalib.util.StringUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-06-15
 */
@Data
@Slf4j
public class TemplateModel {

    @JSONField(name = "NAME")
    private String name;


    /**
     * 对模板参数进行转换
     * @param content 待转换的内容
     * @return 转换后的内容
     */
    public String convert(String content) {
        String paramsStr = JSON.toJSONString(this);
        log.info(paramsStr);
        Map<String, Object> paramsMap = JSON.parseObject(paramsStr, new TypeReference<Map<String, Object>>() {});
        for (String key : paramsMap.keySet()) {
            if (StringUtils.isNotEmpty(key) && content.contains(key)) {
                Object value = paramsMap.get(key);
                if (Objects.isNull(value)) {
                    log.error("将模板占位替换成值-值为空-该占位值为{}", key);
                } else {
                    content = content.replaceAll(key, value.toString());
                }
            }
        }
        return content;
    }

}
