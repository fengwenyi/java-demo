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
public class XmlItem implements Serializable {

    @JacksonXmlProperty(localName = "Data")
    private String data;


}
