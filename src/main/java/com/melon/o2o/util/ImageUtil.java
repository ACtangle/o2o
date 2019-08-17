package com.melon.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName ImageUtil
 * @Description
 * @Author melon
 * @Date 2019-08-17 18:10
 * @Version
 */

public class ImageUtil {

    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final Random r = new Random();

    public static String generateThumbnail(CommonsMultipartFile thumbnail, String targetAddr) {
        //重新命名
        String realFileName = getRandomFileName();
        //获取文件格式
        String extension = getFileExtension(thumbnail);
        //创建目录
        makeDirPath(targetAddr);
        String relativePath = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativePath);

        try {
            Thumbnails.of(thumbnail.getInputStream()).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.8f)
                    .toFile(dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return relativePath;
    }

    /**
     * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
     *
     * @return
     */
    private static String getRandomFileName() {
        //获取随机五位数
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /**
     * 获取输入文件流的扩展名，文件格式 .jpg .png
     * @param cFile
     * @return
     */
    private static String getFileExtension(CommonsMultipartFile cFile){
        String originalFileName = cFile.getOriginalFilename();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /**
     * 创建目标路径所涉及的目录，即/Users/melon/Pictures/o2o/xxx.jpg
     * 所有的文件夹都应该有
     * @param targetAddr
     */
    private static void makeDirPath( String targetAddr){
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            //所有的文件都创建
            dirPath.mkdirs();
        }
    }

    public static void main(String[] args) throws Exception {
        Thumbnails.of(new File("/Users/melon/Pictures/desktopImage/IMG_0077.jpg"))
                .size(1200, 1200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f).outputQuality(0.8f)
                .toFile("/Users/melon/Pictures/desktopImage/IMG_0077new.jpg");
    }
}
