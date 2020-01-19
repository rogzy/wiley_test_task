package api.response;

import lombok.Getter;

import java.util.List;

@Getter
public class Product {
    String name;
    List<Image> images;
}
