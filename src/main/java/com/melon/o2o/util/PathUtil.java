package com.melon.o2o.util;

/**
 * @ClassName PathUtil
 * @Description
 * @Author melon
 * @Date 2019-08-17 18:43
 * @Version
 */

public class PathUtil {

    private static String separator = System.getProperty("file.separator");

    //根据不同的操作环境即操作系统选择根目录
    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/projectdev/image/";
        } else {
//            basePath = "/home/melon/image/";
            basePath = "/Users/melon/Pictures/o2o/";

        }
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    //相对子路径
    public static String getShopImagePath(long shopId) {
        String imagePath = "upload/item/shop/" + shopId + "/";
        return imagePath.replace("/", separator);
    }


}
