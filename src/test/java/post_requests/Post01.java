package post_requests;

import base_url.JsonPlaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends JsonPlaceholderBaseUrl {
    /*
    Given
      1) https://jsonplaceholder.typicode.com/todos
      2)
    When
       I send POST Request to the Url
    Then
       Status code is 201 {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
                         }
    And
       response body is like {
                               "userId": 55,
                               "title": "Tidy your room",
                               "completed": false,
                               "id": 201
                               }
    */

    @Test
    public void post01() {
        // Set the Url
        spec.pathParam("1", "todos");

        // Set the expected Data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = obj.expectedDataMap(55, "Tidy your room", false);

        // Send the Request and Get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{1}");
        response.prettyPrint();

        // Do Assertion
        Map<String ,Object> actualData=response.as(HashMap.class);
        System.out.println(expectedData);
        System.out.println(actualData);
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));

    }
}
