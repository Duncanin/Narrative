package com.example.narrative.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.example.narrative.Service.BookService;
import com.example.narrative.model.Book;


@Controller
@RequestMapping("/admin/books")
public class AdminBookController {
    private final BookService bookService;

    public AdminBookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 例如顯示書籍列表
    @RequestMapping("/list")
    public String showBookList(Model model) {
        List<Book> books = bookService.findAll(); // 獲取所有書籍
        model.addAttribute("books", books); // 將書籍列表添加到模型中
        return "admin/book/list"; // 返回書籍列表的視圖名稱
    }
}
