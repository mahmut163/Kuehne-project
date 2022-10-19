package apitest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookPayload {
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("collectionOfIsbns")
    private List<CollectionOfIsbns> collectionOfIsbns;
    public BookPayload(String userId, List<CollectionOfIsbns> collectionOfIsbns) {
        this.userId = userId;
        this.collectionOfIsbns = collectionOfIsbns;
    }


}
