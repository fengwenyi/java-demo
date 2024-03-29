package com.fengwenyi.demo.okhttp;

import com.fengwenyi.javalib.file.FileUtils;
import okhttp3.*;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author Erwin Feng
 * @since 2021-01-26
 */
public class DownloadUtils {

    private static DownloadUtils downloadUtil;
    private final OkHttpClient okHttpClient;

    public static DownloadUtils get() {
        if (downloadUtil == null) {
            downloadUtil = new DownloadUtils();
        }
        return downloadUtil;
    }

    public DownloadUtils() {
//        okHttpClient = new OkHttpClient();
        okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(5, TimeUnit.SECONDS) //连接超时
                .readTimeout(180, TimeUnit.SECONDS) //读取超时
                .writeTimeout(180, TimeUnit.SECONDS) //写超时
//                .addInterceptor(new CommonHeaderInterceptor())
//                .addInterceptor(new CacheInterceptor())
//                .addInterceptor(new HttpLoggerInterceptor())
//                .addNetworkInterceptor(new EncryptInterceptor())
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();
    }


    /**
     * @param url          下载连接
     * @param destFileDir  下载的文件储存目录
     * @param destFileName 下载文件名称，后面记得拼接后缀，否则手机没法识别文件类型
     * @param listener     下载监听
     */

    public void download(final String url, final String destFileDir, final String destFileName, final OnDownloadListener listener) {

        Request request = new Request.Builder()
                .url(url)
//                .addHeader()
                //HttpServletResponse resp
                //.addHeader("Content-Length", "1024")
                .build();

//        OkHttpClient client = new OkHttpClient();

        /*try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //异步请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败监听回调
                listener.onDownloadFailed(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;

                //储存下载文件的目录
                File dir = new File(destFileDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, destFileName);
                if (file.exists()) {
                    System.out.println("警告：文件已存在，新下载的文件将被重命名！");
                    String fileNameTemp = destFileName.substring(0, destFileName.lastIndexOf("."));
                    file = new File(dir, fileNameTemp + "_" + System.currentTimeMillis() + "." + FileUtils.getSuffix(destFileName));
                }

                try {

                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        //下载中更新进度条
                        listener.onDownloading(progress);
                    }
                    fos.flush();
                    //下载完成
                    listener.onDownloadSuccess(file);
                } catch (Exception e) {
                    listener.onDownloadFailed(e);
                }finally {

                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {

                    }

                }


            }
        });
    }


    public interface OnDownloadListener{

        /**
         * 下载成功之后的文件
         */
        void onDownloadSuccess(File file);

        /**
         * 下载进度
         */
        void onDownloading(int progress);

        /**
         * 下载异常信息
         */

        void onDownloadFailed(Exception e);
    }


}
