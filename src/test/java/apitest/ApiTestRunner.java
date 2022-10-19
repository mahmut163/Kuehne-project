package apitest;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.TestUtility;
import org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.List;

public class ApiTestRunner {
    private static String configFile = "config.properties";
    private static String baseUrl;
    private static String apiUserName;
    private static String apiPassWord;
    private static String apiUserId;
    private static String bookName="Git Pocket Guide";
    @BeforeClass
    public void setUp(){

        baseUrl = TestUtility.readFromConfigProperties(configFile, "baseurl");
        apiUserName=TestUtility.readFromConfigProperties(configFile, "apiUserName");
        apiPassWord=TestUtility.readFromConfigProperties(configFile, "apiPassWord");
        apiUserId=TestUtility.readFromConfigProperties(configFile, "userId");


    }

    @Test
    public void getBooksFromBookStore()  {
        //Sending  get request
        Response response =RestAssured.when().get(baseUrl);
        //verify status code
        response.then().assertThat().statusCode(200);
        //verify response body
       response.getBody().jsonPath().getString("books.title").equals(bookName);


    }
    String collection=null;
    @Test
    public void addBookToCollection(){
        List<CollectionOfIsbns> collectionOfIsbns=new ArrayList<>();
        CollectionOfIsbns book=new CollectionOfIsbns("9781449365035",
                "Speaking JavaScript","An In-Depth Guide for Programmers","Axel Rauschmayer");
        collectionOfIsbns.add(book);
        Response response=RestAssured.given().headers("Content-Type","application/json").and()
                .auth().preemptive().basic(apiUserName,apiPassWord)
               .body(collection=PayloadUtility.getBookPayload(apiUserId,collectionOfIsbns))

                .when().post(baseUrl);
        response.getBody().prettyPrint();
        //verify status code
        response.then().assertThat().statusCode(201);
        //verify Book is added to collection
      Assert.assertTrue(response.jsonPath().getString("books.title").contains("Speaking JavaScript"));
    }
}
