package com.melon.o2o.service.imp;

import com.melon.o2o.dao.ProductDao;
import com.melon.o2o.dao.ProductImgDao;
import com.melon.o2o.dto.ImageHolder;
import com.melon.o2o.dto.ProductExecution;
import com.melon.o2o.entity.Product;
import com.melon.o2o.entity.ProductImg;
import com.melon.o2o.enums.ProductStateEnum;
import com.melon.o2o.exceptions.ProductOperationException;
import com.melon.o2o.service.ProductService;
import com.melon.o2o.util.ImageUtil;
import com.melon.o2o.util.PageCalculator;
import com.melon.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName ProductServiceImpl
 * @Description
 * @Author melon
 * @Date 2019-08-27 01:51
 * @Version
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImgDao productImgDao;


    /**
     * 1.处理缩略图
     * 2.将product入库,并获取productId
     * 3.结合productId批量处理商品详情图
     * 4.将商品详情图批量入库
     */
    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> thumbnaiList) throws ProductOperationException {

        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            //给商品设置上默认属性
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            //默认为上架状态
            product.setEnableStatus(1);
            //若商品缩略图不为空则添加
            if (thumbnail != null) {
                addThumbnail(product, thumbnail);
            }
            try {
                //创建商品信息
                int result = productDao.insertProduct(product);
                if (result <= 0) {
                    throw new ProductOperationException("创建商品失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品失败:" + e.getMessage());
            }
            //若商品缩略图不为空
            if (thumbnaiList != null && thumbnaiList.size() > 0) {
                addProductImgList(product, thumbnaiList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS);
        } else {
            //传参为空
            return new ProductExecution(ProductStateEnum.EMPTY);
        }

    }

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
        int count = productDao.queryProductCount(productCondition);
        ProductExecution productExecution = new ProductExecution();
        if (productList.size() > 0) {
            productExecution.setProductList(productList);
            productExecution.setCount(count);
            return productExecution;
        } else {
            productExecution.setState(ProductStateEnum.INNER_ERROR.getState());
        }
        return productExecution;
    }

    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductByProductId(productId);
    }

    @Override
    @Transactional
    //1.若缩略图参数有值，则处理缩略图
    //2.若原先存在缩略图则先删除再添加新图，之后获取缩略图相对路径并赋值给product
    //3.将tb_product_img下面的该商品原先的详情图记录全部清除
    //4.更新tb_product的信息
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> thumbnaiList) throws ProductOperationException {
        //空值判断
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            //给商品设置属性
            product.setCreateTime(new Date());
            //若商品缩略图不为空且原有缩略图不为空则删除原来缩略图并添加
            if (thumbnail != null) {
                //先获取一遍原有信息，因为原来的信息有原图片地址
                Product tempProduct = productDao.queryProductByProductId(product.getProductId());
                if (tempProduct.getImgAddr() != null) {
                    ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
                }
                addThumbnail(product, thumbnail);
            }
            //如果有新存入的商品详情图，则将原先的删除，并添加新的图片
            if (thumbnaiList != null && thumbnaiList.size() > 0) {
                deleteProductImgList(product.getProductId());
                addProductImgList(product, thumbnaiList);
            }
            try {
                //更新商品信息
                int result = productDao.updateProduct(product);
                if (result <= 0) {
                    throw new ProductOperationException("更新商品信息失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            } catch (Exception e) {
                throw new ProductOperationException("更新商品信息失败" + e.getMessage());
            }
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }
    }


    private void addThumbnail(Product product, ImageHolder thumbnail) {
        //获取shop图片目录的相对值路径
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        product.setImgAddr(shopImgAddr);
    }

    private void addProductImgList(Product product, List<ImageHolder> thumbnaiList) {
        //获取图片路径，这里直接存放在相应店铺的文件夹底下
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();
        //遍历图片处理，并添加进productImg
        for (ImageHolder productImgHolder : thumbnaiList) {
            String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }
        //如果图片确实存在需要添加，就进行批量操作
        if (productImgList.size() > 0) {
            try {
                int result = productImgDao.batchInsertProductImg(productImgList);
                if (result < 0) {
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品详情图片失败:" + e.getMessage());
            }
        }
    }

    private void deleteProductImgList(long productId) {
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        for (ProductImg productImg : productImgList) {
            ImageUtil.deleteFileOrPath(productImg.getImgAddr());
        }
        productImgDao.deleteProductImgByProductId(productId);
    }
}
