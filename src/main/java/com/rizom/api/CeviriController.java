package com.rizom.api;

import com.rizom.model.Category;
import com.rizom.model.News;
import com.rizom.repo.CategoryRepository;
import com.rizom.repo.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@AllArgsConstructor
@Controller
public class CeviriController {


    private final CategoryRepository categoryRepository;
    private final NewsRepository newsRepository;

    @GetMapping("/ceviri")
    public String ceviriNews(Model model) {
        Category ceviriCategory = categoryRepository.findByName("Çeviri"); // "Çeviri" kategorisini veritabanından alın
        if (ceviriCategory == null) {
            throw new IllegalArgumentException("Çeviri kategorisi bulunamadı.");
        }

        List<News> ceviriNewsList = newsRepository.findByCategory(ceviriCategory);

        model.addAttribute("ceviriCategory", ceviriCategory);
        model.addAttribute("ceviriNewsList", ceviriNewsList);

        return "ceviri";
    }



    @GetMapping("/{categoryName}")
    public String categoryNews(@PathVariable String categoryName, Model model) {
        Category category = categoryRepository.findByName(categoryName);
        if (category == null) {
            throw new IllegalArgumentException("Invalid category name: " + categoryName);
        }

        List<News> newsList = newsRepository.findByCategory(category);

        List<Category> categoryList=categoryRepository.findAll();
        model.addAttribute("categories", categoryList);

        model.addAttribute("category", category);
        model.addAttribute("newsList", newsList);

        return "ceviri";
    }



}
