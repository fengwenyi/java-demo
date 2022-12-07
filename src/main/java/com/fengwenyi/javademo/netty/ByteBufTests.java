package com.fengwenyi.javademo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-12-07
 */
public class ByteBufTests {

    @Test
    public void testReadByte() {

        String str = "1234";
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);
//        byte b1 = byteBuf.readByte();
//        System.out.println(b1);
        ByteBuf byteBuf1 = byteBuf.readBytes(2);
        System.out.println("str: " + str);
        System.out.println("read: " + byteBuf1.toString(StandardCharsets.UTF_8));
        System.out.println("剩下的：" + byteBuf.toString(StandardCharsets.UTF_8));

//        //分配一个非池化的buffer
//        ByteBuf buf = Unpooled.buffer(16);
//        //通过hasArray方法判断Buf的类型是堆缓存区还是直接缓冲区
//        System.out.println(buf.hasArray() ? "ByteBuf is heap buf." : "ByteBuf is direct buf");
//        //获取堆缓冲区中的数据
//        byte[] array=buf.array();
//        byte b = buf.readByte();
//        ByteBuf byteBuf = buf.readBytes(2);
    }

}
