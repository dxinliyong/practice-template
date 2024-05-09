package com.yong.util.pdf;

/**
 * @Author: liyong
 * @Date: 2024/5/8 17:49
 * @Email: dixin_liyong@163.com
 * @Desc：
 */


import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @Author: liyong
 * @Date: 2024/5/8 16:33
 * @Email: dixin_liyong@163.com
 * @link https://blog.csdn.net/mybook201314/article/details/103749216
 * @Desc：
 */

public class PageNumPdfPageEvent extends PdfPageEventHelper{

    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        try {
            // PDF文档内容
            PdfContentByte pdfContent = writer.getDirectContent();

            pdfContent.saveState();
            pdfContent.beginText();

            int footerFontSize = 14 ;

            // 解决页码中文无法显示 或者 显示为乱码的问题
            // 但是必须引入jar包 itext-asian-5.2.0.jar
            BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
            Font fontDetail = new Font(baseFont, footerFontSize, Font.NORMAL);

            pdfContent.setFontAndSize(baseFont, footerFontSize);

            // 页脚的页码 展示
            String footerNum = String.format("第%d页", writer.getPageNumber());
            Phrase phrase = new Phrase(footerNum, fontDetail);

            // 页码的 横轴 坐标 居中
            float x = ( document.left() + document.right() ) / 2 ;
            // 页码的 纵轴 坐标
            float y = document.bottom(-10) ;
            // 添加文本内容，进行展示页码
            ColumnText.showTextAligned(pdfContent, Element.ALIGN_CENTER, phrase, x, y, 0);

            pdfContent.endText();
            pdfContent.restoreState();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


