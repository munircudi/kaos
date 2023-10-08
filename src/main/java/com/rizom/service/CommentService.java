package com.rizom.service;

import com.rizom.model.Comment;
import com.rizom.model.News;
import com.rizom.model.User;
import com.rizom.repo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public List<Comment> getCommentsByNews(News news) {
        return commentRepository.findByNews(news);
    }

    public List<Comment> getCommentsByAuthor(User author) {
        return commentRepository.findByAuthor(author);
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void updateComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
