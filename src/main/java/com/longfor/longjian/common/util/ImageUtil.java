package com.longfor.longjian.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class ImageUtil {

    /**
     * @param srcPath   源图片路径
     * @param desPath   修改大小后图片路径
     * @param scaleSize 图片的修改比例，目标宽度
     */
    public static void resizeImage(String srcPath, String desPath, int scaleSize) throws IOException {

        File srcFile = new File(srcPath);
        Image srcImg = ImageIO.read(srcFile);
//        BufferedImage bi = null;
//        try {
//            bi = ImageIO.read(srcFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        float width = srcImg.getWidth(null); // 像素
        float height = srcImg.getHeight(null); // 像素
        float scale = width / scaleSize;
        BufferedImage buffImg = null;
        buffImg = new BufferedImage(scaleSize, (int) (height / scale), BufferedImage.TYPE_INT_RGB);
        //使用TYPE_INT_RGB修改的图片会变色
        buffImg.getGraphics().drawImage(
                srcImg.getScaledInstance(scaleSize, (int) (height / scale), Image.SCALE_SMOOTH), 0,
                0, null);

//        ImageIO.write(buffImg, "JPEG", new File(desPath));
//        addWaterMark(srcImg, desPath, "sssssssssssssss");
    }

    public static void addWaterMark(String srcImgPath, String tarImgPath, String waterMarkContent, int scaleSize) {
        Color markContentColor = new Color(0, 0, 0, 255);                               //水印图片色彩以及透明度
        Font font = new Font(null, Font.PLAIN, 11);                     //水印字体
        try {
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            int scale = srcImgWidth / scaleSize;
            // 加水印
            BufferedImage bufImg = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
//            setRGB(bufImg.getColorModel(), bufImg.getRaster(), 0, srcImgHeight / scale - 15, scaleSize, 15, 16777215);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, 200, 200, null);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g.setColor(markContentColor); //根据图片的背景设置水印颜色
            g.setFont(font);              //设置字体

            //设置水印的坐标
            int x = 0;
            int y = 30;
            g.drawString(waterMarkContent, x, y);  //画出水印
            x = 0;
            y = 50;
            g.drawString("ssssssssssssss", x, y);  //画出水印
            g.dispose();
            // 输出图片
            FileOutputStream outImgStream = new FileOutputStream(tarImgPath);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outImgStream);
//
//            JPEGEncodeParam encodeParam = JPEGCodec.getDefaultJPEGEncodeParam(bufImg);
//            // 设置图片压缩质量
//            encodeParam.setQuality(1, true);
//            encoder.encode(bufImg, encodeParam);
            ImageIO.write(bufImg, "jpg", outImgStream);
            System.out.println("添加水印完成");
            outImgStream.flush();
            outImgStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setRGB(ColorModel colorModel, WritableRaster raster, int startX, int startY, int w, int h, int rgb) {
        Object pixel = null;

        for (int y = startY; y < startY+h; y++) {
            for (int x = startX; x < startX+w; x++) {
                pixel = colorModel.getDataElements(rgb, pixel);
                raster.setDataElements(x, y, pixel);
            }
        }
    }

    public static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

    public static void base64() throws Exception {
        String b = URLEncoder.encode(";", "utf-8");
        String a = URLDecoder.decode("983%2C741%2C1582157%3B730%2C415%2C1582156%3B0%2C0%2C1582155%3B875%2C561%2C1582154%3B1019%2C594%2C1582153%3B755%2C607%2C1582152%3B981%2C715%2C1582151%3B758%2C786%2C1582150%3B545%2C651%2C1582149%3B505%2C653%2C1582148%3B240%2C727%2C1582147%3B88%2C603%2C1582146%3B139%2C505%2C1582145%3B1129%2C401%2C1582144%3B1017%2C419%2C1582143%3B108%2C553%2C1582142%3B894%2C731%2C1582141%3B544%2C608%2C1582140%3B217%2C739%2C1582139%3B187%2C724%2C1582138",
                "utf-8");
        System.out.println(a);
    }

    public static void main(String[] args) throws Exception {
//        resizeImage("E:/image/src.jpg", "E:/image/des.jpg", 100);//将图片压缩至100宽
//        base64();
        String srcImgPath = "E:/image/src.jpg"; //源图片地址
        String tarImgPath = "E:/image/des.jpg"; //待存储的地址
        String waterMarkContent = "张顺东-恒辉保温-海晶-项目经理 2019-01-31";  //水印内容
        addWaterMark(srcImgPath, tarImgPath, waterMarkContent, 200);
    }
}