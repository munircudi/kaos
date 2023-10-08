package com.rizom.api;

import com.rizom.model.Comment;
import com.rizom.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public String getAllComments(Model model) {
        List<Comment> commentList = commentService.getAllComments();
        model.addAttribute("commentList", commentList);
        return "comments";
    }

    @GetMapping("/comments/{id}")
    public String getCommentById(@PathVariable Long id, Model model) {
        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment", comment);
        return "comment-details";
    }

    @GetMapping("/comments/create")
    public String createCommentForm(Model model) {
        model.addAttribute("comment", new Comment());
        return "comment-create";
    }

    @PostMapping("/comments/create")
    public String createComment(@ModelAttribute Comment comment) {
        commentService.saveComment(comment);
        return "redirect:/comments";
    }

    @GetMapping("/comments/edit/{id}")
    public String editCommentForm(@PathVariable Long id, Model model) {
        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment", comment);
        return "comment-edit";
    }

    @PostMapping("/comments/edit/{id}")
    public String editComment(@PathVariable Long id, @ModelAttribute Comment comment) {
        comment.setId(id);
        commentService.updateComment(comment);
        return "redirect:/comments";
    }

    @GetMapping("/comments/delete/{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return "redirect:/comments";
    }
}
