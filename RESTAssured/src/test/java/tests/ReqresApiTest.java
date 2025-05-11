package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ReqresApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void createUser_thenGetUserById() {
        String name = "John Doe";
        String job = "Engineer";

        Response postResponse = given()
            .header("Content-Type", "application/json")
            .header("x-api-key", "reqres-free-v1")
            .body("{ \"name\": \"" + name + "\", \"job\": \"" + job + "\" }")
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .body("id", notNullValue())
            .log().all()
            .extract().response();

        String userId = postResponse.path("id");

//      This code block will be used to test that the record was added as expected.
//      Since this is a demo project, the post request did not actually add the record so we can not fetch that

//        when()
//            .get("/users/" + userId)
//        .then()
//            .statusCode(200)
//            .body("data.id", equalTo(Integer.parseInt(userId)))
//            .body("data.name", equalTo(name))
//            .body("data.job", equalTo(job));
    }
}
