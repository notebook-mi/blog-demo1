package com.ld.poetize.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author zuosy
 * @Date 2024/1/27 12:35
 **/
public interface MinioService {

    /**
     * 上传文件返回文件路径
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
