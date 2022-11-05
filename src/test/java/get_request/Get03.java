package get_request;

import base_url.JsonPlaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Get03 extends JsonPlaceholderBaseUrl {

    /*
      Given
          https://jsonplaceholder.typicode.com/todos/23
      When
          User send GET Request to the URL
      Then
          HTTP Status Code should be 200
      And
          Response format should be "application/json"
      And
          "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
      And
          "completed" is false
      And
          "userId" is 2
   */

    @Test
    public void get01() {

        // Set The Url
        spec.pathParams("first","todos","second",23); // first ve second yerine 1 ve 2 de yazilabilir,String olmak sartiyla

        // Expected Data

        // Send the request and Get Response
        /*Response response = given().spec(spec.pathParams("first", "todos", "second", 23)).when().get("/{first}/{second}");*/
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do Assertion
        // hard assert : farklı body
        // soft assert : aynı body
        /*
        Soft assert testi gerçekleştirir ve assert başarısız olursa hata fırlatma gerçekleştirmez.
        Hard assert anında hata fırlatır, sonrasında test işlemine devam eder
         */
        // 1. Yol (Hard Assert)
        response.then().assertThat().statusCode(200).
                contentType("application/json").
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed", equalTo(false)).
                body("userId", equalTo(2));

        // 2.Yol (Soft Assert)
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false), "userId", equalTo(2));
    }
}
