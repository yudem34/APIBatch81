package get_requests;

import base_url.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10b extends GoRestBaseUrl {
    /*
   Given
       https://gorest.co.in/public/v1/users/2986
   When
       User send GET Request to the URL
   Then
       Status Code should be 200
   And
       Response body should be like
    {
    "meta": null,
    "data": {
        "id": 2986,
        "name": "Navin Talwar",
        "email": "navin_talwar@mclaughlin.name",
        "gender": "male",
        "status": "inactive"
    }
    }
    */


    @Test
    public void get01() {
        //1. Set The URL
        spec.pathParams("first", "users", "second", 2986);
        // 2. Set The Expected Data ( put, post, patch)
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("id", 2986);
        expectedData.put("name", "Navin Talwar");
        expectedData.put("email", "navin_talwar@mclaughlin.name");
        expectedData.put("gender", "male");
        expectedData.put("status", "inactive");
        System.out.println(expectedData);

        Map<String, Object> metaMap = new HashMap<>();
        metaMap.put("meta", null);
        metaMap.put("data", expectedData);

        // 3. Send The Request And Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        // 4. Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(expectedData.get("id"),((Map) actualData.get("data")).get("id"));
        assertEquals(expectedData.get("name"),((Map) actualData.get("data")).get("name"));
        assertEquals(expectedData.get("email"),((Map) actualData.get("data")).get("email"));
        assertEquals(expectedData.get("gender"),((Map) actualData.get("data")).get("gender"));
        assertEquals(expectedData.get("status"),((Map) actualData.get("data")).get("status"));
        assertEquals(metaMap.get("meta"), actualData.get(null));

    }
}