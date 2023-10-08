package com.rizom.repo;

import com.rizom.model.Category;
import com.rizom.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByCategory(Category category);
    List<News> findByFeaturedTrue();
    List<News> findTop4ByOrderByDateDesc();


}
