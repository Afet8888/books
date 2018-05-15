package az.itstep.azjava.testapp.repository;

import az.itstep.azjava.testapp.model.Book;
import az.itstep.azjava.testapp.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
