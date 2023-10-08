package com.rizom.service;

import com.rizom.model.Category;
import com.rizom.model.News;
import com.rizom.repo.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class NewsService {
    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }


    public List<News> getFeaturedNews() {
        return newsRepository.findByFeaturedTrue();
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public News getNewsById(Long id) {
        return newsRepository.findById(id).orElse(null);
    }

    public List<News> getNewsByCategory(Category category) {
        return newsRepository.findByCategory(category);
    }

    public void updateNews(News news) {
        newsRepository.save(news);
    }

    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

    public void saveOrUpdateNews(News news, MultipartFile imageFile) throws IOException {

        if (!imageFile.isEmpty()) {
            byte[] imageBytes = imageFile.getBytes();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            news.setImageUrl(base64Image);
        }
        newsRepository.save(news);
    }

    public List<News> getLatest4News() {
        return newsRepository.findTop4ByOrderByDateDesc();
    }
}
