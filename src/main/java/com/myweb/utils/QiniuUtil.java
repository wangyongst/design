package com.myweb.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class QiniuUtil {

    private static Logger logger = LoggerFactory.getLogger(QiniuUtil.class);
    private static String accessKey = "q4AkOSaHjD43aUWwXNQgwcHVe0s2vwuJYh77-phs";
    private static String secretKey = "8Dz3l0uc556U2i9COZDs23OJoWwB9_flIlN_5b7R";
    private static String bucket = "design";

    public static Result upload(InputStream inputStream, String key) {
        Result result = new Result();
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(inputStream, key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            result.setStatus(1);
            result.setData(putRet.key);
        } catch (QiniuException ex) {
            Response r = ex.response;
            result.setMessage(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                ex2.printStackTrace();
            }
        }
        return result;
    }
}
