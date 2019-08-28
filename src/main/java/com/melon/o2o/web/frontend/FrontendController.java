package com.melon.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName FrontendController
 * @Description
 * @Author melon
 * @Date 2019-08-29 05:08
 * @Version
 */

@RequestMapping(value = "/frontend")
@Controller
public class FrontendController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    private String index() {
        return "frontend/index";
    }
}
