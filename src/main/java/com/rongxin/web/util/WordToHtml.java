//package com.rongxin.web.util;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.UUID;
//
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//
//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.converter.PicturesManager;
//import org.apache.poi.hwpf.converter.WordToHtmlConverter;
//import org.apache.poi.hwpf.usermodel.PictureType;
//import org.apache.poi.util.IOUtils;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.w3c.dom.Document;
//
//import fr.opensagres.poi.xwpf.converter.core.ImageManager;
//import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
//import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
//
//public class WordToHtml {
//    private static final Logger logger = LoggerFactory.getLogger(WordToHtml.class);
//    public File docxConvert(String parentDirectory, InputStream is,String newName) {
//        try {
//            //XWPFDocument document = new XWPFDocument(new FileInputStream(filename));
//
//            XWPFDocument document = new XWPFDocument(is);
//            XHTMLOptions options = XHTMLOptions.create().setImageManager(new ImageManager(new File(parentDirectory), UUID.randomUUID().toString())).indent(4);
//            FileOutputStream out = new FileOutputStream(new File(parentDirectory + newName+ ".html"));
//            XHTMLConverter.getInstance().convert(document, out, options);
//            return new File(parentDirectory + newName+ ".html");
//        } catch (IOException ex) {
//            logger.error("word转化出错！", ex);
//            return null;
//        }
//
//    }
//
//
//    public File docConvert(String parentDirectory,InputStream istream ,String newName) {
//        try {
//            HWPFDocument document = new HWPFDocument(istream);
//            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
//                    DocumentBuilderFactory.newInstance().newDocumentBuilder()
//                            .newDocument());
//
//            // converter默认对图片不作处理，需要手动下载图片并嵌入到html中
//            wordToHtmlConverter.setPicturesManager(new PicturesManager() {
//                @Override
//                public String savePicture(byte[] bytes, PictureType pictureType, String s, float v, float v1) {
//                    String imageFilename = parentDirectory + "";
//                    String identity=UUID.randomUUID().toString();
//                    File imageFile = new File(imageFilename, identity+s);
//                    imageFile.getParentFile().mkdirs();
//                    InputStream in = null;
//                    FileOutputStream out = null;
//
//                    try {
//                        in = new ByteArrayInputStream(bytes);
//                        out = new FileOutputStream(imageFile);
//                        IOUtils.copy(in, out);
//
//                    } catch (IOException ex) {
//                        logger.error("word转化出错！", ex);
//                    } finally {
//                        if (in != null) {
//                            IOUtils.closeQuietly(in);
//                        }
//
//                        if (out != null) {
//                            IOUtils.closeQuietly(out);
//                        }
//
//                    }
//                    return imageFile.getName();
//                }
//            });
//
//            wordToHtmlConverter.processDocument(document);
//            Document htmlDocument = wordToHtmlConverter.getDocument();
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            DOMSource domSource = new DOMSource(htmlDocument);
//            StreamResult streamResult = new StreamResult(out);
//
//            TransformerFactory tf = TransformerFactory.newInstance();
//            Transformer serializer = tf.newTransformer();
//            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
//            serializer.setOutputProperty(OutputKeys.METHOD, "html");
//            serializer.transform(domSource, streamResult);
//            out.close();
//
//            String result = new String(out.toByteArray());
//            FileWriter writer = new FileWriter(parentDirectory + newName + ".html");
//            writer.write(result);
//            writer.close();
//        } catch (IOException | TransformerException | ParserConfigurationException ex) {
//            logger.error("word转化出错！", ex);
//        }
//        return new File(parentDirectory + newName + ".html");
//    }
//
//
//
//}
//
//<dependency>
//<groupId>org.apache.poi</groupId>
//<artifactId>poi-scratchpad</artifactId>
//<version>4.1.2</version>
//</dependency>
//
//<dependency>
//<groupId>fr.opensagres.xdocreport</groupId>
//<artifactId>fr.opensagres.xdocreport.converter.docx.xwpf</artifactId>
//<version>2.0.2</version>
//</dependency>