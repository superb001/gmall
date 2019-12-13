package com.hpu.gmall.manage;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageWebApplicationTests {

    @Test
    public void contextLoads() throws IOException, MyException {

        String imgUrl = "http://q1vmbg2k9.bkt.clouddn.com";

        //七牛后端直传测试

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Zone.zone1());   //Zone.zone1() :华北
        // Configuration cfg = new Configuration("https://upload-z1.qiniup.com/");
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成上传凭证，然后准备上传
        String accessKey = "t1rj_ITdLZjEqLONZ6h5XV468JdyKFHymqWIt0VE";
        String secretKey = "v4L9BAFsyTJsgFgmDoCXNJMpIR3uxj-IvLpwHWrA";
        String bucket = "liuguoguo";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "D:/upload/666.jpg";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "888.png";  //设置key为filename，存储空间的文件名为filename值，返回key不等于hash值
        //String key = "null";  设置key为null，存储空间的文件名为hash值，返回key=hash值
        //简单认证
        Auth auth = Auth.create(accessKey, secretKey);
        //生成上传token
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            //设置key为null时，hash=key
            System.out.println(putRet.key);  //888.png
            System.out.println(putRet.hash);  //Fnu7e8dpw7g9Dsq4enl5lJeb2W6l  这个hash值保证了同一文件，改名字也不可以重复上传
            System.out.println("url路径： " + imgUrl + "/"+putRet.key); //http://q1vmbg2k9.bkt.clouddn.com/888.png
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }


        //fastdfs上传测试

        /*// 获得配置文件tracker.conf的路径，tracker.conf里面配置有tracker_server的ip地址
        String tracker = GmallManageWebApplicationTests.class.getResource("/tracker.conf").getPath();
        // 通过读取配置文件,配置fdfs的全局链接地址
        ClientGlobal.init(tracker);

        // 以后新建的client自动带ip
        TrackerClient trackerClient = new TrackerClient();

        // 通过trackerClient获得一个trackerServer的实例
        TrackerServer trackerServer = trackerClient.getConnection();

        // 通过trackerServer获得一个Storage链接客户端
        StorageClient storageClient = new StorageClient(trackerServer,null);

        //图片元数据不上传，设为:null  fastdfs服务器返回值为uploadInfos：图片在服务器上的url路径
        String[] uploadInfos = storageClient.upload_file("d:/upload/1.jpg", "jpg", null);

        String url = "http://121.36.59.75";
       // 迭代输出uploadInfos内的信息
        for (String uploadInfo : uploadInfos) {
            System.out.println("uploadInfo" + "->  " + uploadInfo);
            url += "/"+uploadInfo;
            //url = url + "/" + uploadInfo;
        }
        System.out.println(url);*/
    }

}
