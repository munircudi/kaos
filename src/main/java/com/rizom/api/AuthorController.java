package com.rizom.api;

import com.rizom.model.Author;
import com.rizom.model.AuthorGroup;
import com.rizom.model.Category;
import com.rizom.service.AuthorService;
import com.rizom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class AuthorController {
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public AuthorController(AuthorService authorService, CategoryService categoryService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @GetMapping("admin/yazarlar/{id}")
    public String getAuthorByIdForAdmin(@PathVariable Long id, Model model) {
        Author author = authorService.getAuthorById(id);
        if (author != null) {
            model.addAttribute("author", author);
            return "admin/author-details";
        }
        return "admin/author-not-found";
    }

    @GetMapping("/create-author")
    public String showCreateAuthorForm() {
        return "admin/create-author";
    }

    @PostMapping("/create-author")
    public String createAuthor(@RequestParam String name, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        Author author = new Author();
        author.setName(name);
        authorService.saveOrUpdateNews(author,imageFile);
        return "redirect:/yazarlar"; // veya başka bir sayfaya yönlendirme
    }


    @GetMapping("/yazarlar/{id}")
    public String getAuthorById(@PathVariable Long id, Model model) {
        Author author = authorService.getAuthorById(id);
        if (author != null) {

            List<Category> categoryList=categoryService.getAllCategories();
            model.addAttribute("categories", categoryList);

            model.addAttribute("author", author);
            return "author-details"; // Yönlendirme
        }
        return "author-not-found";
    }


    @GetMapping("admin/yazarlar")
    public String getAllAuthorsForAdmin(Model model) {
        List<AuthorGroup> authorGroups = authorService.getAuthorsGroupedByFirstLetter();
        model.addAttribute("authorGroups", authorGroups);
        return "admin/authors";
    }

    @GetMapping("yazarlar")
    public String getAllAuthors(Model model) {
        List<AuthorGroup> authorGroups = authorService.getAuthorsGroupedByFirstLetter();
        model.addAttribute("authorGroups", authorGroups);
        return "authors";
    }
}
