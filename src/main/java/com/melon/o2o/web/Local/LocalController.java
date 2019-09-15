package com.melon.o2o.web.Local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName LocalController
 * @Description
 * @Author melon
 * @Date 2019-09-14 23:31
 * @Version
 */

@Controller
@RequestMapping("/local")
public class LocalController {

    @RequestMapping(value = "/accountbind", method = RequestMethod.GET)
    private String accountBind() {
        return "local/accountBind";
    }

    @RequestMapping(value = "/changepsw", method = RequestMethod.GET)
    private String changePsw() {
        return "local/changepsw";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    private String login() {
        return "local/login";
    }


}
