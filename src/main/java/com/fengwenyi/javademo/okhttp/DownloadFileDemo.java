package com.fengwenyi.javademo.okhttp;

import com.fengwenyi.javalib.util.PrintUtils;

import java.io.File;

/**
 * @author Erwin Feng
 * @since 2021-01-26
 */
public class DownloadFileDemo {

    public static void main(String[] args) {
//        String url = "https://github-production-release-asset-2e65be.s3.amazonaws.com/137451403/e86c1e80-571d-11eb-9c3b-749b45cafe90?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20210126%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210126T144135Z&X-Amz-Expires=300&X-Amz-Signature=6921479a9b625cae918ffda3358a9a51bd4fca6c7ec4533d56d7c1cc6ef573df&X-Amz-SignedHeaders=host&actor_id=23391492&key_id=0&repo_id=137451403&response-content-disposition=attachment%3B%20filename%3Dnacos-server-1.4.1.tar.gz&response-content-type=application%2Foctet-stream";
        String url = "https://github-production-release-asset-2e65be.s3.amazonaws.com/137451403/e86c1e80-571d-11eb-9c3b-749b45cafe90?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20210126%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210126T162054Z&X-Amz-Expires=300&X-Amz-Signature=5443ab7b7a1b5085fc42a8316992e489a811980a101c8559c4cd30437926b303&X-Amz-SignedHeaders=host&actor_id=0&key_id=0&repo_id=137451403&response-content-disposition=attachment%3B%20filename%3Dnacos-server-1.4.1.tar.gz&response-content-type=application%2Foctet-stream";
        String filename = "nacos-server.tar.gz";
        downFile(url, filename);
//        String url = "https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF";
//        String filename = "images.jfif";
//        downFile(url, filename);
    }


    /**
     * 文件下载
     */
    private static void downFile(String url, String filename) {
        DownloadUtils.get().download(url, "F:\\Download", filename,
                new DownloadUtils.OnDownloadListener() {
                    @Override
                    public void onDownloadSuccess(File file) {
                        System.out.print("100%");
                        System.out.println();
                    }

                    @Override
                    public void onDownloading(int progress) {
                        /*int n = progress % 5;
                        if (n == 0) {
                            PrintUtils.info(progress + "%");
                        }*/
                        /*for (int i = 0; i < progress; i++) {
                            System.out.print("-");
                        }*/
                        System.out.print("-");
                    }

                    @Override
                    public void onDownloadFailed(Exception e) {
                        e.printStackTrace();
                    }
                });


    }

}
