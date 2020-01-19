package api.response;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchResponse {
    List<Suggestion> suggestions;
    List<Product> products;
    List<Page> pages;
    Boolean showSeeAllProducts;
    Boolean showSeeAllPages;
}
