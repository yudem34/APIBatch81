package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonplaceholderBaseUrl {
    /*
    Given
       https://jsonplaceholder.typicode.com/todos/2
   When
       I send GET Request to the URL
   Then
       Status code is 200
       And "completed" is false
       And "userId" is 1
       And "title" is "quis ut nam facilis et officia qui"
       And header "Via" is "1.1 vegur"
       And header "Server" is "cloudflare"
       {
           "userId": 1,
           "id": 2,
           "title": "quis ut nam facilis et officia qui",
           "completed": false
       }
     */

    @Test
    public void get01() {

        //De-Serialization: Json datayı Java objesine çevirme
        //Serialization: Java objesini Json formatına çevirme.
        //De-Serialization: Iki türlü yapacağız.
        //Gson: Google tarafından üretilmiştir.
        //Object Mapper: Daha popüler...


        // Set the Url
        spec.pathParams("first", "todos","second",2);
        // Set The Expected Data
        Map<String,Object> expectedData=new HashMap<>();  // LinkedHashMap sirali yapmak icin kullanabiliriz
        expectedData.put("userId",1);
        expectedData.put("id",2);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("completed",false);
        System.out.println(expectedData);
        // Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        // Do Assertion
        JsonPath jsonPath=response.jsonPath();
        Map<String,Object> actualData=response.as(HashMap.class);
        System.out.println(actualData);
        assertEquals(expectedData,actualData);
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("id"),actualData.get("id"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals("1.1 vegur", response.header("Via"));
        assertEquals("cloudflare", response.header("Server"));
        assertEquals(200, response.statusCode());

    }
}
