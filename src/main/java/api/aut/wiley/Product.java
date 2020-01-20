package api.aut.wiley;

import lombok.Getter;

import java.util.List;

@Getter
public class Product {
    String name;
    List<Image> images;
}
