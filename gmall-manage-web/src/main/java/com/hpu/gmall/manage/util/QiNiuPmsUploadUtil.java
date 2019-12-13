package com.hpu.gmall.manage.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @ClassName: QiNiuPmsUploadUtil
 * @Description: qiniu文件上传工具类：服务器后端文件直传
 * @Author: L7O dachaoliu1@163.com
 * @Date: 2019/12/13 16:03
 * @Version: V1.0
 */
public class QiNiuPmsUploadUtil {

    public static String uploadImage(MultipartFile multipartFile) {
        // 需要写入项目静态类
        String imgUrl = "http://q1vmbg2k9.bkt.clouddn.com";  //七牛空间固定的域名，可以自己买一个绑上去
        String accessKey = "t1rj_ITdLZjEqLONZ6h5XV468JdyKFHymqWIt0VE";
        String secretKey = "v4L9BAFsyTJsgFgmDoCXNJMpIR3uxj-IvLpwHWrA";
        String bucket = "liuguoguo";  //开辟的七牛存储空间

        //构造一个带指定 Region（Zone）对象的配置类
        Configuration cfg = new Configuration(Zone.zone1());   //Zone.zone1() :华北
        // Configuration cfg = new Configuration("https://upload-z1.qiniup.com/");
        UploadManager uploadManager = new UploadManager(cfg);

        // 生成上传凭证，然后准备上传

        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        //String localFilePath = "D:/upload/666.jpg";


        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null; //设置文件名字,没必要
        //简单认证
        Auth auth = Auth.create(accessKey, secretKey);
        //根据七牛存储空间，生成上传token
        String upToken = auth.uploadToken(bucket);
        try {
            // 获得上传的二进制对象
            byte[] bytes = multipartFile.getBytes();
            // 获得完整文件名
            String originalFilename = multipartFile.getOriginalFilename();
            Response response = uploadManager.put(bytes, key, upToken);
            //Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            //设置key为null时，hash=key
            System.out.println(putRet.key);  //Fnu7e8dpw7g9Dsq4enl5lJeb2W6l
            System.out.println(putRet.hash);  //Fnu7e8dpw7g9Dsq4enl5lJeb2W6l
            imgUrl = imgUrl +"/"+putRet.key;
            System.out.println("url路径： " + imgUrl);

        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgUrl;
    }



}
