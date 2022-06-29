package com.fengwenyi.javademo.jackson;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-06-22
 */
@Data
@JacksonXmlRootElement(localName = "Response")
public class XmlResponse<T> implements Serializable {

    /**
     * 响应状态码. 1-成功、0-失败
     */
    @JacksonXmlProperty(localName = "ResultCode")
    private String resultCode;

    /**
     * 响应信息
     */
    @JacksonXmlProperty(localName = "ResultMsg")
    private String resultMsg;

    /**
     * Item 列表数据
     */
    @JacksonXmlProperty(localName = "Items")
    private List<T> items;

}
