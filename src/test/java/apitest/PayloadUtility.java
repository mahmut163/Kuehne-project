package apitest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class PayloadUtility {
public static String getBookPayload(String usrId, List<CollectionOfIsbns> collectionOfIsbns){
    String payload= null;
   BookPayload bookPayload=new BookPayload(usrId,collectionOfIsbns);
    ObjectMapper objectMapper = new ObjectMapper();
    try {
        payload = objectMapper.writeValueAsString(bookPayload);
    } catch (JsonProcessingException e) {
        e.printStackTrace();
    }
    return payload;

}
}
