package az.itstep.azjava.testapp.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String text;
    private Integer book_id;
    private Integer user_id;

}
