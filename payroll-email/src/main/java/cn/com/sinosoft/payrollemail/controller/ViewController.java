package cn.com.sinosoft.payrollemail.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by fangzy on 2018/8/11 13:06
 */
@Controller
public class ViewController {

    /**
     * 跳转到上传页面
     * @return
     */
    @GetMapping("/upload")
    public String toUpLoad(){
        return "upload";
    }
}
