package az.itstep.azjava.testapp.controller;

import az.itstep.azjava.testapp.model.Comment;
import az.itstep.azjava.testapp.service.CommentService;
import az.itstep.azjava.testapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/comments")

public class CommentController {

    private CommentService commentService;

    @GetMapping
    List<Comment> getAll(@RequestHeader HttpHeaders headers) {
        return commentService.getAll(headers.get("token").get(0));
    }

    @GetMapping("/{id}")
    Comment getById(@PathVariable Integer id, @RequestHeader HttpHeaders headers) {
        return commentService.getById(id, headers.get("token").get(0));
    }

    @PostMapping
    Comment save(@RequestBody Comment comment, @RequestHeader HttpHeaders headers) {
        return commentService.save(comment, headers.get("token").get(0));
    }

    @PutMapping
    Comment update(@RequestBody Comment comment, @RequestHeader HttpHeaders headers) {
        return commentService.update(comment, headers.get("token").get(0));
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id, @RequestHeader HttpHeaders headers) {
        commentService.delete(id, headers.get("token").get(0));
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

}
