package com.example.narrative.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("welcomeMessage", "Hello ~ ");
        //包含HTML標籤訊息
        model.addAttribute("systemNotice","<strong>系統公告<strong> : <br> 1.新增查詢功能。 <br> 2.新增報名功能。");
        //包含特殊字符訊息
        model.addAttribute("specialMessage","早鳥報名優惠 & 學生生份折扣 <70% off>");
        return "index"; // 對應 index.html
    }
}