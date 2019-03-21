package com.longfor.longjian.common.util;

import com.sun.imageio.plugins.png.PNGImageWriter;
import com.sun.imageio.plugins.png.PNGImageWriterSpi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @use 利用Java代码给图片加水印
 * @author yanglei
 */
@Slf4j
public class WaterMarkUtils {

    private static final String PIN="planview_pin.png";

    /**
     * @param srcImgPath 源图片路径
     * @param tarImgPath 保存的图片路径
     * @param waterMarkContent 水印内容
     * @param markContentColor 水印颜色
     * @param font 水印字体
     */
    public static void addWaterMark(String srcImgPath, String tarImgPath, String waterMarkContent, Color markContentColor, Font font,int posx,int posy,int markWidth,int markHeight) {

        try(FileOutputStream outImgStream = new FileOutputStream(tarImgPath)) {
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            log.info("读取图片原始宽:"+srcImgWidth);
            log.info("读取图片原始高:"+srcImgHeight);
            // 加水印
            BufferedImage bufImg =ImageIO.read(srcImgFile);
            Graphics2D g = bufImg.createGraphics();
            g.drawRect( posx, posy-markHeight/2, markWidth, markHeight);
            g.setColor(markContentColor); //根据图片的背景设置水印颜色
            g.setFont(font);              //设置字体
            //PIN
            //if(pinFile.exists()) {
                log.info("in draw pin file...........................");
                BufferedImage pinImg = ImageIO.read(WaterMarkUtils.class.getResourceAsStream("/"+PIN));
                g.drawImage(pinImg, posx - pinImg.getWidth(), posy - pinImg.getHeight(), pinImg.getWidth(), pinImg.getHeight(), null);
           // }
            //设置水印的坐标
            //int x = srcImgWidth - 2*getWatermarkLength(waterMarkContent, g);
            //int y = srcImgHeight - 2*getWatermarkLength(waterMarkContent, g);

            g.drawString(waterMarkContent, posx+2, posy+2);  //画出水印
            g.dispose();

            // 输出图片
            //FileOutputStream outImgStream = new FileOutputStream(tarImgPath);
            ImageIO.write(bufImg, "png", outImgStream);
            System.out.println("添加水印完成");
            outImgStream.flush();
           // outImgStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
            log.error("生成图片错误",e);
        }
    }
    public static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

    public static void cutSize(String src_fn,String dst_fn,Integer posx,Integer posy,Integer width,Integer height){
        try(FileOutputStream outImgStream = new FileOutputStream(dst_fn)){
            // 读取原图片信息
            File oldFile = new File(src_fn);//得到文件
            /** 对服务器上的临时文件进行处理 */
            Image srcFile = ImageIO.read(oldFile);
            int w = srcFile.getWidth(null);
            //  System.out.println(w);
            int h = srcFile.getHeight(null);
            //  System.out.println(h);
            double bili;
            if(width>0){
                bili=width/(double)w;
                height = (int) (h*bili);
            }else{
                if(height>0){
                    bili=height/(double)h;
                    width = (int) (w*bili);
                }
            }
            int left=posx-width/2,top=posy-height/2;
            left=(left<0?0:left);
            top=(top<0?0:top);
            /** 宽,高设定 */
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(srcFile, left, top, width, height, null);
            //String filePrex = oldFile.getName().substring(0, oldFile.getName().indexOf('.'));
            /** 压缩后的文件名 */
            //newImage = filePrex + smallIcon+  oldFile.getName().substring(filePrex.length());
            //PIN
            File pinFile=new File(WaterMarkUtils.class.getResource("/"+PIN).getFile());
            if(pinFile.exists()) {
                BufferedImage pinImg = ImageIO.read(pinFile);
                tag.getGraphics().drawImage(pinImg, left - pinImg.getWidth(), top - pinImg.getHeight(), pinImg.getWidth(), pinImg.getHeight(), null);
            }
             ImageIO.write(tag, "png", outImgStream);
            outImgStream.flush();
            // outImgStream.close();
        }catch (Exception e){
            e.printStackTrace();
            log.error("image error",e);
        }
    }

    public static void resize(String src_fn,String dst_fn,Integer width,Integer height){
        try(FileOutputStream outImgStream = new FileOutputStream(dst_fn)){
            // 读取原图片信息
            File oldFile = new File(src_fn);//得到文件
            /** 对服务器上的临时文件进行处理 */
            Image srcFile = ImageIO.read(oldFile);
            int w = srcFile.getWidth(null);
            //  System.out.println(w);
            int h = srcFile.getHeight(null);
            //  System.out.println(h);
            double bili;
            if(width>0){
                bili=width/(double)w;
                height = (int) (h*bili);
            }else{
                if(height>0){
                    bili=height/(double)h;
                    width = (int) (w*bili);
                }
            }

            /** 宽,高设定 */
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);
            //String filePrex = oldFile.getName().substring(0, oldFile.getName().indexOf('.'));
            /** 压缩后的文件名 */
            //newImage = filePrex + smallIcon+  oldFile.getName().substring(filePrex.length());

            ImageIO.write(tag, "png", outImgStream);
            outImgStream.flush();
            // outImgStream.close();
        }catch (Exception e){
            e.printStackTrace();
            log.error("image error",e);
        }
    }
    public static Pair<Integer,Integer> getImageInfo(String filePath) throws IOException {
        File srcImgFile = new File(filePath);//得到文件
        Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
        int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
        int srcImgHeight = srcImg.getHeight(null);//获取图片的高
        return Pair.of(srcImgWidth,srcImgHeight);
    }
    /*public static void main(String[] args) {
        Font font = new Font("微软雅黑", Font.PLAIN, 35);                     //水印字体
        String srcImgPath="H:/安静时写写/写写博客/Java实现给图片添加水印/s.jpg"; //源图片地址
        String tarImgPath="H:/安静时写写/写写博客/Java实现给图片添加水印/t.jpg"; //待存储的地址
        String waterMarkContent="图片来源：http://blog.csdn.net/zjq_1314520";  //水印内容
        Color color=new Color(255,255,255,128);                               //水印图片色彩以及透明度
        //new WaterMarkUtils().addWaterMark(srcImgPath, tarImgPath, waterMarkContent,color,font);

    }*/
}
