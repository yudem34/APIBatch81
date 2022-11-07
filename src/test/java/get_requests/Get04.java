package get_requests;

import base_url.JsonPlaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get04 extends JsonPlaceholderBaseUrl {
    /*
    Given
           https://jsonplaceholder.typicode.com/todos
    When
      I send a GET request to the Url
    And
        Accept type is "application/json"
    Then
        HTTP Status Code should be 200
    And
        Response format should be "application/json"
    And
        There should be 200 todos
    And
        "quis eius est sint explicabo" should be one of the todos title
    And
        2, 7, and 9 should be among the userIds
    */

    @Test
    public void get01() {
        // 1. Set The Url
        spec.pathParam("first", "todos");
        // 2. Set The expected Data

        // 3. Send The request And get The Response
        Response response = given().spec(spec).when().accept(ContentType.JSON).get("/{first}");
        //response.prettyPrint();

        // 4. Do Assertion

        /* 1.Yol */
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("todos",hasSize(200),
                        "title", hasItem("quis eius est sint explicabo"),
                        "userId", hasItems(2, 7, 9));

        /* 2.Yol */
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", hasItem("quis eius est sint explicabo"),
                        "userId",hasItems(2,7,9));

        JsonPath json=response.jsonPath();
        List<Integer> idler=json.getList("findAll{it.id>0}.id");
        //System.out.println(idler.size());
        assertEquals(200,idler.size());
    }
}
