package api.response;

import lombok.Getter;

@Getter
public class Page {
    String id;
    Double boost;
    String title;
    String url;
    String content;

}
