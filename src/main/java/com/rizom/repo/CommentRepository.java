package com.rizom.repo;

import com.rizom.model.Comment;
import com.rizom.model.News;
import com.rizom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByNews(News news);
    List<Comment> findByAuthor(User author);
}
