package com.melon.o2o.web.shopadmin;

import com.melon.o2o.dto.Result;
import com.melon.o2o.entity.ProductCategory;
import com.melon.o2o.entity.Shop;
import com.melon.o2o.enums.ProductCategoryEnum;
import com.melon.o2o.enums.ShopStateEnum;
import com.melon.o2o.exceptions.ShopOperationException;
import com.melon.o2o.service.ProductCategoryService;
import com.melon.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName ProductCategoryManagementController
 * @Description
 * @Author melon
 * @Date 2019-08-25 02:24
 * @Version
 */

@Controller
@RequestMapping(value = "/shopadmin")
public class ProductCategoryManagementController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request) {

        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        List<ProductCategory> productCategoryList = null;
        if (currentShop != null && currentShop.getShopId() > 0) {
            productCategoryList = productCategoryService.queryProductCategoryList(currentShop.getShopId());
            return new Result<List<ProductCategory>>(true,productCategoryList);
        }else {
            ProductCategoryEnum productCategoryEnum = ProductCategoryEnum.INNER_ERROR;
            return new Result<List<ProductCategory>>(false,productCategoryEnum.getState(),productCategoryEnum.getStateInfo());
        }
    }
}
