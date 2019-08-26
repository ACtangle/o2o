package com.melon.o2o.util;

import com.melon.o2o.dto.ImageHolder;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

@Slf4j
public class ImageUtil {

    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final Random random = new Random();

    public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {

        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail.getImageName());
        makeDirPath(targetAddr);
        String relativePath = targetAddr + realFileName + extension;
        log.debug("current complete relativeAddr is:" + relativePath);
        File dest = new File(PathUtil.getImgBasePath() + relativePath);
        log.debug("current complete addr is:" + PathUtil.getImgBasePath() + relativePath);
        log.debug("basePath:" + basePath);

        try {
            Thumbnails.of(thumbnail.getImage()).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.8f)
                    .toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relativePath;
    }

    //处理缩略图
    public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {

        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail.getImageName());
        makeDirPath(targetAddr);
        String relativePath = targetAddr + realFileName + extension;
        log.debug("current complete relativeAddr is:" + relativePath);
        File dest = new File(PathUtil.getImgBasePath() + relativePath);
        log.debug("current complete addr is:" + PathUtil.getImgBasePath() + relativePath);
        log.debug("basePath:" + basePath);

        try {
            Thumbnails.of(thumbnail.getImage()).size(337,640)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.8f)
                    .toFile(dest);
        } catch (IOException e) {
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
        int rannum = random.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /**
     * 获取输入文件流的扩展名，文件格式 .jpg .png
     *
     * @param fileName
     * @return
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 创建目标路径所涉及的目录，即/Users/melon/Pictures/o2o/xxx.jpg
     * 所有的文件夹都应该有
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            //所有的文件都创建
            dirPath.mkdirs();
        }
    }

    /**
     * storePath是文件路径还是目录文件
     * 如果为文件路径则删除该文件
     * 如果为目录路径则删除该目录下的所有文件
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath){
        File fileOrPath =  new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists()) {
            //如果为目录
            if (fileOrPath.isDirectory()) {
                File[] files= fileOrPath.listFiles();
                for (int i =0 ; i<files.length ; i++) {
                    files[i].delete();
                }
            }
            fileOrPath.delete();
        }
    }


    public static void main(String[] args) throws Exception {
        Thumbnails.of(new File("/Users/melon/Pictures/desktopImage/IMG_0077.jpg"))
                .size(1200, 1200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f).outputQuality(0.8f)
                .toFile("/Users/melon/Pictures/desktopImage/IMG_0077new.jpg");
    }
}
