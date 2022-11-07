package odev;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.ReqresPojo;
import test_data.ReqresTestData;
import utils.ObjectMapperUtils;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Soru3Object_Map_Pojo extends ReqresBaseUrl {
    //3: Map ile ve Pojo Class ile ayrı ayrı Object Mapper kullanarak yapınız.

/*
        Given
            1) https://reqres.in/api/users/2
            2) {
                "name": "morpheus",
                "job": "zion president"
                }
        When
            I send Put Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "zion president",
                                                "updatedAt": "2022-10-02T11:35:05.693Z"
                                            }
*/

    @Test
    public void objectMap() throws IOException {
        // 1. Set The Url
        spec.pathParams("first", "users", "second", 2);

        // 2. Set The expected Data
        ReqresTestData obj = new ReqresTestData();
        String jsonInString = obj.dataKeyString("morpheus", "zion president");

        HashMap expectedData = new ObjectMapper().readValue(jsonInString, HashMap.class);
        System.out.println("expectedData = " + expectedData);

        // 3. Send The request And get The Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();

        // 4. Do Assertion
        HashMap actualData = new ObjectMapper().readValue(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);

        // Assertion ile
        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));

        JsonPath jsonPath=response.jsonPath();
        // Gson ile
        assertEquals("morpheus",jsonPath.getString("name"));
        assertEquals("zion president",jsonPath.getString("job"));
    }

    @Test
    public void objectPojo() throws IOException {

        // 1. Set The Url
        spec.pathParams("first", "users", "second", 2);

        // 2. Set The expected Data
        ReqresPojo expectedData=new ReqresPojo("morpheus", "zion president");
        System.out.println("expectedData = " + expectedData);

        // 3. Send The request And get The Response

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first}/{second}");
        response.prettyPrint();


        // 4. Do Assertion
        ReqresPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), ReqresPojo.class);
        System.out.println("actualData = " + actualData);


        // Assertion ile
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(expectedData.getJob(),actualData.getJob());

        JsonPath jsonPath=response.jsonPath();
        // Gson ile
        assertEquals("morpheus",jsonPath.getString("name"));
        assertEquals("zion president",jsonPath.getString("job"));


    }
}
