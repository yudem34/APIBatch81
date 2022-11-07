package odev;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.ReqresPojo;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Soru4Gson extends ReqresBaseUrl {
    //4: Map ile ve Pojo Class ile ayrı ayrı Gson kullanarak yapınız.
    /*
       Given
           1) https://reqres.in/api/users/2
           2) {
                "name": "neo"
               }
       When
            I send PATCH Request to the Url
       Then
             Status code is 200
             And response body is like   {
                                                "name": "neo",
                                                "updatedAt": "2022-10-02T12:53:21.675Z"
                                         }
    */

    @Test
    public void mapGson() {
        // 1. Set The Url
        spec.pathParams("first", "users", "second", 2);

        // 2. Set The expected Data
        ReqresTestData obj = new ReqresTestData();
        Map<String,String> expectedData=obj.dataKeyMap("neo",null);
        System.out.println("expectedData = " + expectedData);

        // 3. Send The request And get The Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
        JsonPath jsonPath=response.jsonPath();
        jsonPath.prettyPrint();

        // 4. Do Assertion
        HashMap actualData=response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals("neo",jsonPath.getString("name"));
    }

    @Test
    public void pojoGson() {
        // 1. Set The Url
        spec.pathParams("first", "users", "second", 2);

        // 2. Set The expected Data
        ReqresPojo expectedData = new ReqresPojo("neo",null);
        System.out.println("expectedData = " + expectedData);

        // 3. Send The request And get The Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().patch("/{first}/{second}");
        JsonPath jsonPath=response.jsonPath();
        jsonPath.prettyPrint();

        // 4. Do Assertion
        ReqresPojo actualData=response.as(ReqresPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals("neo",jsonPath.getString("name"));

        // pojo ile
        assertEquals(expectedData.getName(),actualData.getName());
    }
}
