package com.fengwenyi.javademo.localedemo;

import org.junit.Test;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-12-30
 */
public class LocaleDemo {

    @Test
    public void init() {
        //①带有语言和国家/地区信息的本地化对象
        Locale locale1 = new Locale("zh","CN");

        //②只有语言信息的本地化对象
        Locale locale2 = new Locale("zh");

        //③等同于Locale("zh","CN")
        Locale locale3 = Locale.CHINA;

        //④等同于Locale("zh")
        Locale locale4 = Locale.CHINESE;

        //⑤获取本地系统默认的本地化对象
        Locale locale5 = Locale.getDefault();
    }

    @Test
    public void testNumberFormat() {
        Locale locale = new Locale("zh", "CN");
        NumberFormat currFmt = NumberFormat.getCurrencyInstance(locale);
        double amt = 123456.78;
        System.out.println(currFmt.format(amt));
    }

    @Test
    public void testDateFormat() {
        Locale locale = new Locale("en", "US");
        Date date = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        System.out.println(df.format(date));
    }

    @Test
    public void testMessageFormat() {
        //①信息格式化串
        String pattern1 = "{0}，你好！你于{1}在工商银行存入{2} 元。";
        String pattern2 = "At {1,time,short} On {1,date,long}，{0} paid {2,number, currency}.";

        //②用于动态替换占位符的参数
        Object[] params = {"John", new GregorianCalendar().getTime(),1.0E3};

        //③使用默认本地化对象格式化信息
        String msg1 = MessageFormat.format(pattern1,params);

        //④使用指定的本地化对象格式化信息
        MessageFormat mf = new MessageFormat(pattern2,Locale.US);
        String msg2 = mf.format(params);
        System.out.println(msg1);
        System.out.println(msg2);
    }

}
