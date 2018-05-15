package az.itstep.azjava.testapp.service;

import az.itstep.azjava.testapp.exceptions.CommentExistException;
import az.itstep.azjava.testapp.exceptions.CommentNotFoundException;
import az.itstep.azjava.testapp.exceptions.WrongUserDataException;
import az.itstep.azjava.testapp.model.Comment;
import az.itstep.azjava.testapp.model.User;
import az.itstep.azjava.testapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private UserService userService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    @Override
    public List<Comment> getAll(String token) {
        return (List<Comment>) commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment, String token) {
        User user = userService.findByToken(token);
        if (Objects.isNull(user))
            throw new WrongUserDataException("User Not Found");
        if (Objects.isNull(comment))
            throw new CommentNotFoundException("CommentNotFound");
        if (Objects.isNull(comment.getText())
                || Objects.isNull(comment.getTitle()))
            throw new CommentNotFoundException("No comment data");
        if(Objects.nonNull(comment.getId())){
            if(commentRepository.existsById(comment.getId()))
                throw new CommentExistException("Comment is Exist");
        }

        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment, String token) {

        if (Objects.isNull(comment))
            throw new CommentNotFoundException("CommentNotFound");
        User user = userService.findByToken(token);
        if (Objects.isNull(user))
            throw new WrongUserDataException("User Not Found");
        if(user.getRole().equals("ADMIN"))
            throw new WrongUserDataException("Wrong User Data");
        if (!user.getId().equals(comment.getUser_id()))
            throw new WrongUserDataException("This User Not Permitted");
        if (Objects.isNull(comment.getText())
                || Objects.isNull(comment.getId())
                || Objects.isNull(comment.getTitle()))
            throw new CommentNotFoundException("No comment data");
        if(!commentRepository.existsById(comment.getId()))
            throw new CommentNotFoundException("No such comment");
        return commentRepository.save(comment);
    }

    @Override
    public Comment getById(Integer id, String token) {
        User user = userService.findByToken(token);
        if (Objects.isNull(user))
            throw new WrongUserDataException("User Not Found");
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id, String token) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if(Objects.isNull(comment))
            throw new CommentNotFoundException("Comment not found");
        User user = userService.findByToken(token);
        if (Objects.isNull(user))
            throw new WrongUserDataException("User Not Found");
        if(user.getRole().equals("USER")){
            if(!comment.getUser_id().equals(user.getId())){
                throw new WrongUserDataException("Not permitted to do this");
            }
        }

        commentRepository.deleteById(id);
    }

}
