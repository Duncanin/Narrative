package com.example.narrative.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.narrative.Service.CardMediumService;
import com.example.narrative.model.CardMedium;

@Controller
@RequestMapping("/admin/cardMediums")
public class AdminCardMediumController {
    private final CardMediumService cardMediumService;

    public AdminCardMediumController(CardMediumService cardMediumService) {
        this.cardMediumService = cardMediumService;
    }

    // 例如顯示卡片列表
    @RequestMapping("/list")
    public String showCardMediumList(Model model) {
        List<CardMedium> cardMediums = cardMediumService.findAll(); // 獲取所有卡片
        model.addAttribute("cardMediums", cardMediums); // 將卡片列表添加到模型中
        return "admin/card_medium/list"; // 返回卡片列表的視圖名稱
    }
    
}
