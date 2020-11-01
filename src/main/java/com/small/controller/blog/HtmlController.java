package com.small.controller.blog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xueshiqi
 * @since 2020/9/15
 */
@Controller
public class HtmlController {
    private static final Logger logger = LogManager.getLogger();

    @RequestMapping("/{htmlName}.html")
    public String getIndex(@PathVariable String htmlName) {
        return "html/"+htmlName;
    }

}
