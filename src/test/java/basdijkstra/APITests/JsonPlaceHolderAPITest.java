package basdijkstra.APITests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


public class JsonPlaceHolderAPITest {
    
    @BeforeClass
    public static void tearUp(){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
    }


    @ParameterizedTest
    @CsvSource({
            "1, Leanne Graham,    harness real-time e-markets",
            "2, Ervin Howell,     synergize scalable supply-chains",
            "3, Clementine Bauch, e-enable strategic applications"
    })
    public void checkUserInformation(String userId, String name, String bs){
        given()
        .pathParam("user", userId)
        .when()
        .get("/users/{user}")
        .then()
        .statusCode(200)
        .and()
        .assertThat().contentType(equalTo("application/json; charset=utf-8"))
        .and()
        .body("name", equalTo(name))
        .and()
        .body("company.bs", equalTo(bs));

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
