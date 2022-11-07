package odev;

import base_url.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.ReqresPojo;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Soru2 extends ReqresBaseUrl {
    /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
    */

    @Test
    public void soru2() {
        // 1. Set The Url
        spec.pathParam("first", "users");

        // 2. Set The expected Data
        ReqresTestData obj = new ReqresTestData();
        Map<String, String> expectedData = obj.dataKeyMap("morpheus", "leader");

        // 3. Send The request And get The Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        // 4. Do Assertion
        HashMap actualData=response.as(HashMap.class);
        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));

    }

    @Test
    public void pojo() {
        // 1. Set The Url
        spec.pathParam("first", "users");

        // 2. Set The expected Data
        ReqresPojo expectedData=new ReqresPojo("morpheus", "leader");

        // 3. Send The request And get The Response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        // 4. Do Assertion
        ReqresPojo actualData=response.as(ReqresPojo.class);
        assertEquals(expectedData.getName(),actualData.getName());
        assertEquals(expectedData.getJob(),actualData.getJob());
    }
}
