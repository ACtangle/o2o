package com.melon.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.melon.o2o.dto.ImageHolder;
import com.melon.o2o.dto.ProductExecution;
import com.melon.o2o.entity.Product;
import com.melon.o2o.entity.ProductCategory;
import com.melon.o2o.entity.Shop;
import com.melon.o2o.enums.ProductStateEnum;
import com.melon.o2o.exceptions.ProductCategoryOperationException;
import com.melon.o2o.service.ProductCategoryService;
import com.melon.o2o.service.ProductService;
import com.melon.o2o.util.CodeUtil;
import com.melon.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ProductManagementController
 * @Description
 * @Author melon
 * @Date 2019-08-27 19:43
 * @Version
 */

@Controller
@RequestMapping(value = "/shopadmin")
public class ProductManagementController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 支持上传商品详情图的最大数量
     */
    private static final int IMG_MAX_COUNT = 6;

    @RequestMapping(value = "/getproductbyid", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    private Map<String, Object> getProductById(@RequestParam Long productId) {
        Map<String, Object> modelMap = new HashMap<>();
        //非空判断
        if (productId > -1) {
            //获取商品信息
            Product product = productService.getProductById(productId);
            //获取该店铺下的商品类别列表
            List<ProductCategory> productCategoryList = productCategoryService.queryProductCategoryList(product.getShop().getShopId());
            modelMap.put("success", true);
            modelMap.put("product", product);
            modelMap.put("productCategoryList", productCategoryList);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty productId");
        }
        return modelMap;
    }

    @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addProduct(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<>();
        //验证码校验
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }

        //接受前端参数的变量的初始化，包括商品、缩略图、详情图列表实体类
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        String productStr = HttpServletRequestUtil.getString(request, "productStr");
        MultipartHttpServletRequest multipartRequest = null;
        ImageHolder thumbnail = new ImageHolder();
        List<ImageHolder> productImgList = new ArrayList<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        try {
            //若请求存在文件流，则取出相关的文件（包含缩略图和详情图）
            if (multipartResolver.isMultipart(request)) {
                multipartRequest = (MultipartHttpServletRequest) request;
                //取出缩略图构建ImageHolder
                CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
                thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());
                //取出详情图并构建List对象，最多支持六张图片上传
                for (int i = 0; i < IMG_MAX_COUNT; i++) {
                    CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartRequest.getFile("productImg" + i);
                    if (productImgFile != null) {
                        //若取出的第i个详情图片文件流不为空，则加入详情图列表
                        ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(), productImgFile.getInputStream());
                        productImgList.add(productImg);
                    } else {
                        //若取出的第i个图片详情图片为空，则终止循环
                        break;
                    }
                }
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "上传图片不能为空");
            }
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        try {
            //获取前端表单传过来的string并转为实体类
            product = mapper.readValue(productStr, Product.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        //若product信息不为空，缩略图和详情图列表不为空，则开始进行商品入库操作
        if (product != null && thumbnail != null && productImgList.size() > 0) {
            try {
                //从session中获取当前店铺的id并赋值给product，减少对前端数据的依赖
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                Shop shop = new Shop();
                shop.setShopId(currentShop.getShopId());
                product.setShop(shop);
                //执行添加操作
                ProductExecution productExecution = productService.addProduct(product, thumbnail, productImgList);
                if (productExecution.getState() == ProductStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", productExecution.getStateInfo());
                }
            } catch (ProductCategoryOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入商品信息");
        }
        return modelMap;
    }

    @RequestMapping(value = "/modifyproduct", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> modifyProduct(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        //是商品编辑时候调用还是上下架操作的时候后调用
        //若为前者则进行验证码判断，后者则跳过验证码判断
        boolean statusChange = HttpServletRequestUtil.getBoolean(request, "statusChange");
        if (!statusChange && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        //接受前端参数的变量的初始化，包括商品、缩略图、详情图列表实体类
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        String productStr = HttpServletRequestUtil.getString(request, "productStr");
        MultipartHttpServletRequest multipartRequest = null;
        ImageHolder thumbnail = new ImageHolder();
        List<ImageHolder> productImgList = new ArrayList<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        try {
            //若请求存在文件流，则取出相关的文件（包含缩略图和详情图）
            if (multipartResolver.isMultipart(request)) {
                multipartRequest = (MultipartHttpServletRequest) request;
                //取出缩略图构建ImageHolder
                CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
                thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());
                //取出详情图并构建List对象，最多支持六张图片上传
                for (int i = 0; i < IMG_MAX_COUNT; i++) {
                    CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartRequest.getFile("productImg" + i);
                    if (productImgFile != null) {
                        //若取出的第i个详情图片文件流不为空，则加入详情图列表
                        ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(), productImgFile.getInputStream());
                        productImgList.add(productImg);
                    } else {
                        //若取出的第i个图片详情图片为空，则终止循环
                        break;
                    }
                }
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "上传图片不能为空");
            }
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        try {
            productStr = HttpServletRequestUtil.getString(request, "productStr");
            product = mapper.readValue(productStr, Product.class);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        //若product信息不为空，缩略图和详情图列表不为空，则开始进行商品入库操作
        if (product != null) {
            try {
                //从session中获取当前店铺的id并赋值给product，减少对前端数据的依赖
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                Shop shop = new Shop();
                shop.setShopId(currentShop.getShopId());
                product.setShop(shop);
                //开始进行商品信息变更
                ProductExecution productExecution = productService.modifyProduct(product, thumbnail, productImgList);
                if (productExecution.getState() == ProductStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", productExecution.getStateInfo());
                }
            } catch (ProductCategoryOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入商品信息");
        }
        return modelMap;


    }




}
