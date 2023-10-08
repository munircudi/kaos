package com.rizom.api;

import com.rizom.model.Author;
import com.rizom.model.Category;
import com.rizom.model.News;
import com.rizom.service.AuthorService;
import com.rizom.service.CategoryService;
import com.rizom.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

@AllArgsConstructor
@Controller
public class NewsController {

    private final NewsService newsService;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    private List<String> fileNames;


    @GetMapping("/adm")

    public String kjksdjskjds()
    {
        return "admin/index";
    }


    @GetMapping("/news/form")
    public String showNewsForm(Model model) {

        List<Author> allAuthors=authorService.getAllAuthors();

        model.addAttribute("files", fileNames);

        model.addAttribute("authors", allAuthors);
        List<Category> categoryList=categoryService.getAllCategories();
        model.addAttribute("categories", categoryList);
        model.addAttribute("news", new News());
        return "news-form";
    }

    @PostMapping("/news/create")
    public String createNews(@ModelAttribute News news, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        newsService.saveOrUpdateNews(news, imageFile);
        return "redirect:/news-list";
    }



    @GetMapping("/news/category/{categoryId}")
    public String getNewsByCategory(@PathVariable Long categoryId, Model model) {
        Category category = categoryService.getCategoryById(categoryId);
        List<News> newsListByCategory = newsService.getNewsByCategory(category);
        model.addAttribute("newsList", newsListByCategory);
        return "index";
    }

    @GetMapping("/search")
    public String searchResults(@RequestParam("query") String query, Model model) {
        // Arama sorgusunu küçük harfe çevir
        String lowercaseQuery = query.toLowerCase();

        // Veritabanından tüm haberleri al
        List<News> allNews = newsService.getAllNews();

        // Arama sonuçlarını depolamak için bir liste oluştur
        List<News> searchResults = new ArrayList<>();

        // Her bir haberi kontrol et ve sorgu ile eşleşenleri bul
        for (News news : allNews) {
            String title = news.getTitle().toLowerCase();
            String content = news.getContent().toLowerCase();
            String categoryAndAuthor = (news.getCategory().getName() + '/' + news.getAuthor().getName()).toLowerCase();

            if (title.contains(lowercaseQuery) || content.contains(lowercaseQuery) || categoryAndAuthor.contains(lowercaseQuery)) {
                searchResults.add(news);
            }
        }

        // Arama sorgusu boşsa sonuçları temizle
        if (query.isEmpty()) {
            searchResults.clear();
        }

        model.addAttribute("query", query);
        model.addAttribute("searchResults", searchResults);

        return "search-result";
    }




    @GetMapping("/news-list")
    public String getAllNews(Model model) {
        List<News> newsList = newsService.getAllNews();
        model.addAttribute("newsList", newsList);
        return "news-list";
    }

    @GetMapping("/news/{id}")
    public String getNewsById(@PathVariable Long id, Model model) {

        List<Category> categoryList=categoryService.getAllCategories();
        model.addAttribute("categories", categoryList);
        
        News news = newsService.getNewsById(id);
        model.addAttribute("news", news);
        return "single";
    }

    @GetMapping("/news/edit/{id}")
    public String editNewsForm(@PathVariable Long id, Model model) {

        List<Author> allAuthors=authorService.getAllAuthors();
        model.addAttribute("authors", allAuthors);
        List<Category> categoryList=categoryService.getAllCategories();
        model.addAttribute("categories", categoryList);
        News news = newsService.getNewsById(id);
        model.addAttribute("news", news);
        return "admin/news-edit";
    }

    @PostMapping("/news/edit/{id}")
    public String editNews(@PathVariable Long id, @ModelAttribute News news) {
        news.setId(id);
        newsService.updateNews(news);
        return "redirect:/news-list";
    }

    @GetMapping("/news/delete/{id}")
    public String deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return "redirect:/news-list";
    }

    @GetMapping("/list")
    public String listNews(Model model) {
        model.addAttribute("newsList", newsService.getAllNews());
        return "index";
    }

    @GetMapping("/create")
    public String createNewsForm(Model model) {
        model.addAttribute("news", new News());
        return "admin/create-news";
    }




}