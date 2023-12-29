package com.yupi.nawoj_backend.manager;

import cn.hutool.core.lang.UUID;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.yupi.nawoj_backend.config.AliyunConfig;
import com.yupi.nawoj_backend.model.enums.AppHttpCodeEnum;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @Author peelsannaw
 * @create 29/12/2023 15:09
 */
@Component
public class AliManager {

    private static final String endpoint = AliyunConfig.END_POINT;
    private static final String accessKeyId = AliyunConfig.KEY_ID;
    private static final String accessKeySecret = AliyunConfig.KEY_SECRET;
    private static final String bucketName = AliyunConfig.BUCKET_NAME;

    public static String uploadImg(MultipartFile file){
        try {
            // 创建OSS实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();

            //1 在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            // yuy76t5rew01.jpg
            fileName = uuid+fileName;

            //2 把文件按照日期进行分类
            //获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            //拼接
            fileName = datePath+"/"+fileName;

            //调用oss方法实现上传
            //第一个参数  Bucket名称
            //第二个参数  上传到oss文件路径和文件名称   aa/bb/1.jpg
            //第三个参数  上传文件输入流
            ossClient.putObject(bucketName,fileName , inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            return "https://"+bucketName+"."+endpoint+"/"+fileName;
        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException(AppHttpCodeEnum.PARAM_IMAGE_FORMAT_ERROR.getErrorMessage());
        }
    }
}
