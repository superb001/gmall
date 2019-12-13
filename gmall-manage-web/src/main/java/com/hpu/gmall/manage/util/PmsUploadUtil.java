package com.hpu.gmall.manage.util;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName: PmsUploadUtil
 * @Description: fastdfs文件上传工具类：服务器后端实现文件直传
 * @Author: L7O dachaoliu1@163.com
 * @Date: 2019/12/12 19:54
 * @Version: V1.0
 */
public class PmsUploadUtil {

    public static String uploadImage(MultipartFile multipartFile) {
        // 应该写在静态类中
        String imgUrl = "http://121.36.59.75";

        // 获得配置文件tracker.conf的路径，tracker.conf里面配置有tracker_server的ip地址
        String tracker = PmsUploadUtil.class.getResource("/tracker.conf").getPath();

        // 通过读取配置文件,配置fdfs的全局链接地址
        try {
            ClientGlobal.init(tracker);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 以后新建的client自动带ip
        TrackerClient trackerClient = new TrackerClient();

        // 通过trackerClient获得一个trackerServer的实例
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerClient.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 通过trackerServer获得一个Storage链接客户端
        StorageClient storageClient = new StorageClient(trackerServer,null);

        try {
            // 获得上传的二进制对象
            byte[] bytes = multipartFile.getBytes();
            // 获得完整文件名
            String originalFilename = multipartFile.getOriginalFilename();
            System.out.println(originalFilename);   //git命令图.jpg
            // 获得文件后缀名：jpg
            int i = originalFilename.lastIndexOf(".");
            String extName = originalFilename.substring(i+1);
            System.out.println(extName);           //jpg

            // 通过二进制流上传,返回资源名分两段返回
            String[] uploadInfos = storageClient.upload_file(bytes, extName, null);

            // 图片元数据不上传，设为:null  fastdfs服务器返回值为uploadInfos：图片在服务器上的url路径
            // 通过资源路径上传
            // String[] uploadInfos = storageClient.upload_file("d:/upload/1.jpg", "jpg", null);

            // 迭代输出uploadInfos内的信息，将url资源名追加在一起
            for (String uploadInfo : uploadInfos) {
                System.out.println("uploadInfo" + "->  " + uploadInfo);
                imgUrl += "/"+uploadInfo;
                //url = url + "/" + uploadInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imgUrl;
    }
}
