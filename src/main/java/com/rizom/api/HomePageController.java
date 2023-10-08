package com.rizom.api;

import com.rizom.model.Category;
import com.rizom.model.News;
import com.rizom.model.Tweet;
import com.rizom.model.User;
import com.rizom.repo.CategoryRepository;
import com.rizom.service.NewsService;
import com.rizom.service.TweetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class HomePageController {

    private final NewsService newsService;
    private final TweetService tweetService;

    private final CategoryRepository categoryRepository;


    public void populateNewsAndLatestTweet(Model model) {
        List<News> newsList = newsService.getAllNews();
        model.addAttribute("newsList", newsList);

        List<Category> categoryList=categoryRepository.findAll();
        model.addAttribute("categories", categoryList);

        List<Tweet> allTweets = tweetService.getAllTweets();
        if (!allTweets.isEmpty()) {
            // Sondaki tweet'i alarak son tweet'i sayfada gösterelim
            Tweet latestTweet = allTweets.get(allTweets.size() - 1);
            model.addAttribute("latestTweet", latestTweet.getTweetHtml());
        } else {
            model.addAttribute("latestTweet", "frefer");
        }
    }

    @GetMapping("")
    public String home(Model model) {

        List<News> featuredNewsList = newsService.getFeaturedNews(); // Bu metodun oluşturulması gerekmektedir
        model.addAttribute("featuredNewsList", featuredNewsList);

        // Son 4 haber için bir liste alın
        List<News> latest4News = newsService.getLatest4News();
        model.addAttribute("latest4News", latest4News);

        populateNewsAndLatestTweet(model);
        return "index";
    }


    @GetMapping("/anasayfa")
    public String getHomePage(Model model) {

        List<News> featuredNewsList = newsService.getFeaturedNews(); // Bu metodun oluşturulması gerekmektedir
        model.addAttribute("featuredNewsList", featuredNewsList);

        // Son 4 haber için bir liste alın
        List<News> latest4News = newsService.getLatest4News();
        model.addAttribute("latest4News", latest4News);

        populateNewsAndLatestTweet(model);
        return "index";
    }

    @GetMapping("/asd")
    public String asd(Model model) {

        return "asd";
    }

    @GetMapping("/potentiaagendicon7272")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @GetMapping("/category")
    public String getCategory() {
        return "category";
    }

    @GetMapping("/single")
    public String getSingleNews(Model model) {

        List<Category> categoryList=categoryRepository.findAll();
        model.addAttribute("categories", categoryList);
        return "single";
    }


    @GetMapping("/admin-add-news")
    public String addNews() {
        return "admin/admin-add-news";
    }

    @GetMapping("/admin-add-category")
    public String addCategory() {
        return "admin/admin-add-category";
    }


    @GetMapping("/admin-upload-image")
    public String addPhoto() {
        return "admin/admin-upload-image";
    }

    @GetMapping("/admin-add-contact")
    public String addContact() {
        return "admin/admin-add-contact";
    }


    @GetMapping("/admin-dashboard")
    public String getAdminDashboard() {
        return "admin/admin-dashboard";
    }



}