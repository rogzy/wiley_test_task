package wiley_test_task.api.aut.wiley;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    String name;
    List<Image> images;
}
