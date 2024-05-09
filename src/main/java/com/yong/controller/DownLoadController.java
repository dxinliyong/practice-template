package com.yong.controller;

import com.yong.util.PathUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author: liyong
 * @Date: 2024/5/8 16:32
 * @Email: dixin_liyong@163.com
 * @Desc 下载文件的模板
 */
@Api(tags = {"下载文件"})
@RequestMapping("/download")
@RestController
public class DownLoadController {
    @ApiOperation(value = "下载文件模板controller")
    @GetMapping( value = "v1", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void downloadV1(HttpServletResponse response) {
        String demoPath = "static/demo.pdf";
        String filePath = PathUtils.getFileFromWorkDir(demoPath);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + "demo" + ".pdf");
        try (
                FileInputStream is = new FileInputStream(filePath);
                ServletOutputStream out = response.getOutputStream()
        ) {
            byte[] buffer = new byte[1024 * 1024 * 5];
            int len;
            while ((len = is.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
