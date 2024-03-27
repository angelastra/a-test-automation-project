package basdijkstra.APITests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class JsonPlaceHolderAPITest {
    
    @BeforeClass
    public static void tearUp(){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
    }


    @Test
    public void checkUser1Information(){
        given()
        .pathParam("user", "1")
        .when()
        .get("/users/{user}")
        .then()
        .statusCode(200)
        .and()
        .assertThat().contentType(equalTo("application/json; charset=utf-8"))
        .and()
        .body("name", equalTo("Leanne Graham"))
        .and()
        .body("company.bs", equalTo("harness real-time e-markets"));

    }

    @Test
    public void createBlogPost(){
        given()
        .contentType(ContentType.JSON)
        .when()
        .body(new File(System.getProperty("user.dir")+"/src/test/java/basdijkstra/resources/PostPayload.json"))
        .post("/posts")
        .then()
        .statusCode(201)
        .and()
        .body("id", isA(Integer.class));

    }

}
