package wiley_test_task.api.aut.wiley;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Page {
    String id;
    Double boost;
    String title;
    String url;
    String content;
}
