package com.rizom.api;

import com.rizom.model.Category;
import com.rizom.model.News;
import com.rizom.repo.CategoryRepository;
import com.rizom.repo.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final NewsRepository newsRepository;


    @GetMapping("/edit-category")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/category-list";
    }

    @GetMapping("/create")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new Category());

        return "admin/create-category";
    }

    @PostMapping("/create")
    public String createCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category id: " + id));
        categoryRepository.delete(category);
        return "redirect:/categories";
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

        return "category";
    }


}
