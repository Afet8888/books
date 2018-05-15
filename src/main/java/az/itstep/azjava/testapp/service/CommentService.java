package az.itstep.azjava.testapp.service;

import az.itstep.azjava.testapp.model.Comment;

import java.util.List;

public interface CommentService {

    Comment save(Comment comment, String token);
    Comment update (Comment comment, String token);
    Comment getById (Integer id, String token);
    void delete (Integer id, String token);
    List<Comment> getAll(String token);
}
