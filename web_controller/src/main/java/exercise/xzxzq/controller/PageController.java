package exercise.xzxzq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author : 徐志清
 * @Description :
 * @Time : 2021/1/8 17:47
 * @File : PageController
 */
@Controller
public class PageController {
    @GetMapping("/toPage/{page}")
    public String toPage(@PathVariable String page){
        return page;
    }
}
