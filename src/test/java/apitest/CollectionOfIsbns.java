package apitest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CollectionOfIsbns {
    @JsonProperty("isbn")
    private String isbn;
    @JsonProperty("title")
    private String title;
    @JsonProperty("subTitle")
    private String subTitle;
    @JsonProperty("author")
    private String author;

    public CollectionOfIsbns(String isbn, String title, String subTitle, String author) {
        this.isbn = isbn;
        this.title = title;
        this.subTitle = subTitle;
        this.author = author;
    }
}
