package qa.restapi.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;
}
