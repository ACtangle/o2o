package com.melon.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName ShopAdminController
 * @Description
 * @Author melon
 * @Date 2019-08-19 16:29
 * @Version
 */

@Controller
@RequestMapping(value = "shopadmin",method = RequestMethod.GET)
public class ShopAdminController {

    @RequestMapping(value = "/shopoperation")
    public String shopOperation(){
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList(){
        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanage")
    public String shopManage(){
        return "shop/shopmanage";
    }

    @RequestMapping(value = "/productcategorymanagement")
    public String productCategoryManagement(){
        return "shop/productcategorymanagement";
    }

    @RequestMapping(value = "/productoperation")
    public String productManage(){
        return "shop/productoperation";
    }

    @RequestMapping(value = "/productmanagement")
    public String productManagement(){
        return "shop/productmanagement";
    }

}
