package com.yong.util.pdf;


import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

/**
 * @Author: liyong
 * @Date: 2024/5/8 17:14
 * @Email: dixin_liyong@163.com
 * @link https://blog.csdn.net/mybook201314/article/details/103749216
 * @Desc：
 */
public class PdfUtils {
    public static String addPageNum(String orgPdfPath, String outputPdfPath) {
        try (
                // 输出文件 流
                FileOutputStream fos = new FileOutputStream(outputPdfPath)) {
            // 新建文档，默认A4大小
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, fos);
            // 设置页面监听事件，必须在open方法前
            writer.setPageEvent(new PageNumPdfPageEvent());
            document.open();
            // PDF内容体
            PdfContentByte pdfContent = writer.getDirectContent();
            // 读取 源PDF文件，进行一页一页复制，才能触发 添加页码的  页面监听事件
            PdfReader reader = new PdfReader(orgPdfPath);
            // 获取 源文件总页数
            int num = reader.getNumberOfPages();
            System.out.println("总页数：" + num);
            // 页面数是从1开始的
            for (int i = 1; i <= num; i++) {
                document.newPage();
                // 设置空页码进行展示
                writer.setPageEmpty(false);
                PdfImportedPage page = writer.getImportedPage(reader, i);
                // 复制好的页面，添加到内容去，触发事件监听
                pdfContent.addTemplate(page, 0, 0);
            }

            document.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputPdfPath;
    }

    public static void main(String[] args) {
        String orgPdfPath = "E:\\学习\\practice-template\\static\\demo1.pdf";
        String outputPdfPath = "E:\\学习\\practice-template\\static\\demo2.pdf";
        PdfUtils.addPageNum(orgPdfPath, outputPdfPath);
    }

}
