package com.fengwenyi.javademo.file;

import com.fengwenyi.javalib.convert.JsonUtils;
import io.krakens.grok.api.Grok;
import io.krakens.grok.api.GrokCompiler;
import io.krakens.grok.api.Match;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2022-07-17
 */
@Slf4j
public class LogFileUtils {

    private long pointer = 0; //上次文件大小
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

    ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

    public void realtimeShowLog(File logFile) throws Exception{

        if(logFile == null) {
            throw new IllegalStateException("logFile can not be null");
        }

        //启动一个线程每2秒读取新增的日志信息
        exec.scheduleWithFixedDelay(new Runnable(){

            @Override
            public void run() {

                //获得变化部分
                try {

                    long len = logFile.length();
                    if(len < pointer){
                        log.info("Log file was reset. Restarting logging from start of file.");
                        pointer = 0;
                    }else{

                        //指定文件可读可写
                        RandomAccessFile randomFile= new RandomAccessFile(logFile,"rw");

                        //获取RandomAccessFile对象文件指针的位置，初始位置是0
                        System.out.println("RandomAccessFile文件指针的初始位置:"+pointer);

                        randomFile.seek(pointer);//移动文件指针位置

                        /*String tmp = "";

                        while((tmp = randomFile.readLine()) != null) {
                            // System.out.println("info : " +new String(tmp.getBytes(StandardCharsets.UTF_8)));
                            System.out.println("info : " + tmp);
                            pointer = randomFile.getFilePointer();
                        }*/

                        for (int i = 0; i < 200; i++) {
                            String content = randomFile.readLine();
                            if (StringUtils.hasText(content)) {
                                //log.info("=> {}", content);
//                                log.info("=> {}", new String(content.getBytes(Charset.defaultCharset())));
//                                log.info("=> {}", new String(content.getBytes(Charset.forName("gbk"))));
//                                log.info("=> {}", new String(content.getBytes(Charset.forName("gb2312"))));
                                try {
                                    parseLog(content);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        pointer = randomFile.getFilePointer();

                        randomFile.close();
                    }

                } catch (Exception e) {
                    //实时读取日志异常，需要记录时间和lastTimeFileSize 以便后期手动补充
                    log.error(dateFormat.format(new Date())  + " File read error, pointer: "+pointer);
                } finally {
                    //将pointer 落地以便下次启动的时候，直接从指定位置获取
                }
            }

        }, 0, 10, TimeUnit.SECONDS);

    }
    public void stop(){
        if(exec != null){
            exec.shutdown();
            log.info("file read stop ！");
        }
    }

    private void parseLog(String logContent) {
        /* Create a new grokCompiler instance */
        GrokCompiler grokCompiler = GrokCompiler.newInstance();
        grokCompiler.registerDefaultPatterns();
        grokCompiler.register("DATETIME","[0-9,\\.\\-: ]+");

        // String pattern = "(?m)^\\[%{INT:pid}\\]%{SPACE}%{TIMESTAMP_ISO8601:createTime}%{SPACE}\\[%{DATA:threadName}\\]%{SPACE}%{LOGLEVEL:LEVEL}%{SPACE}%{JAVACLASS:javaClass}#(?<methodName>[a-zA-Z_]+):%{INT:linenumber}%{SPACE}-%{GREEDYDATA:msg}\n";
        String pattern = "%{DATETIME:datetime} %{LOGLEVEL:level} \\[%{GREEDYDATA:applicationName},%{GREEDYDATA:traceId},%{GREEDYDATA:spanId}\\] %{INT:pid} \\[%{NOTSPACE:thread}\\] %{NOTSPACE:classed}\\s*:%{GREEDYDATA:message}";

        /* Grok pattern to compile, here httpd logs */
//         final Grok grok = grokCompiler.compile("%{COMBINEDAPACHELOG}");
        final Grok grok = grokCompiler.compile(pattern);

        /* Line of log to match */
        //String log = "112.169.19.192 - - [06/Mar/2013:01:36:30 +0900] \"GET / HTTP/1.1\" 200 44346 \"-\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.152 Safari/537.22\"";

        Match gm = grok.match(logContent);

        if (gm.isNull()) {
            log.info(logContent);
        } else {
            /* Get the map with matches */
            final Map<String, Object> capture = gm.capture();
            //log.info(JsonUtils.convertString(capture));
            log.info(capture.toString());
        }

    }

    public static void read(String logPath) {
        File tmpLogFile = new File(logPath);
        LogFileUtils v = new LogFileUtils();
        v.pointer = 0;
        try {
            v.realtimeShowLog(tmpLogFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
