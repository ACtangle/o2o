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

    @RequestMapping(value = "/shopOperation")
    public String shopOperation(){
        return "shop/shopoperation";
    }


}