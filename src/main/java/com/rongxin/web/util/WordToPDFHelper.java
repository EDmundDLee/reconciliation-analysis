package com.rongxin.web.util;


import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.lowagie.text.Document;
//import com.lowagie.text.Paragraph;
//import com.lowagie.text.pdf.PdfWriter;
//
//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.extractor.WordExtractor;
//
//import org.apache.poi.hwpf.usermodel.Range;

public class WordToPDFHelper {

    public void wordOfDocxToPdf( InputStream in,String outPutPath){
        try {
            //读取word文档
            XWPFDocument document = null;
            document = new XWPFDocument(in);
            //将word转成pdf
            PdfOptions options = PdfOptions.create();
            try (OutputStream outPDF = Files.newOutputStream(Paths.get(outPutPath ))) {
                PdfConverter.getInstance().convert(document, outPDF, options);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void wordOfDocToPdf(InputStream in,String outPutPath) {
        Document document = new Document();
        try {
//doc 转换pdf 需要表格图片等进行二次处理并且doc 转换pdf 包冲突 暂时注释该段代码
//            HWPFDocument doc = new HWPFDocument(in);
//            WordExtractor we = new WordExtractor(doc);
//            OutputStream file = new FileOutputStream(new File(outPutPath));
//            PdfWriter writer = PdfWriter.getInstance(document, file);
//            Range range = doc.getRange();
//            document.open();
//            writer.setPageEmpty(true);
//            document.newPage();
//            writer.setPageEmpty(true);
//
//            String[] paragraphs = we.getParagraphText();
//            for (int i = 0; i < paragraphs.length; i++) {
//
//                org.apache.poi.hwpf.usermodel.Paragraph pr = range.getParagraph(i);
//                paragraphs[i] = paragraphs[i].replaceAll("\\cM?\r?\n", "");
//                // add the paragraph to the document
//                document.add(new Paragraph(paragraphs[i]));
//            }

            System.out.println("Document testing completed");
        } catch (Exception e) {
            System.out.println("Exception during test");
            e.printStackTrace();
        } finally {
            // close the document
            document.close();
        }
    }
    public static void main(String[] args) {
       // wordToPdf();
    }
}
