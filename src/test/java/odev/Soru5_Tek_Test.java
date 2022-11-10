package odev;

import base_url.PetstoreSwaggerBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.PetstoreSwaggerTestData;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class Soru5_Tek_Test extends PetstoreSwaggerBaseUrl {

     /*
    https://petstore.swagger.io/ documantation adresini kullanarak 'user' oluşturan ve oluşsan bu user'ı silen bir otomasyon kodu yazınız.
    */

    PetstoreSwaggerTestData obj = new PetstoreSwaggerTestData();

    @Test
    public void postUser() {

        // Set the Url
        spec.pathParam("first", "user");

        // Set the expected Data
        Map<String, Object> expectedData = obj.expectedDataMap(2929, "yudem2929", "yusuf", "demir",
                "ydydydydyd123@gmail.com", "123456ydydyd", "0123456789", 3434);

        System.out.println("expectedData = " + expectedData);

        // Send the Request and post the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        // Send the Request and get the response
        spec.pathParams("first", "user", "second", expectedData.get("username"));
        response = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");
        // response.prettyPrint();

        // Do Assertion
        HashMap actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("firstName"), actualData.get("firstName"));
        assertEquals(expectedData.get("lastName"), actualData.get("lastName"));
        assertEquals(expectedData.get("password"), actualData.get("password"));
        assertEquals(expectedData.get("userStatus"), actualData.get("userStatus"));
        assertEquals(expectedData.get("phone"), actualData.get("phone"));
        assertEquals(expectedData.get("email"), actualData.get("email"));
        assertEquals(expectedData.get("username"), actualData.get("username"));

    }


    @Test
    public void deleteUser() {

        //Set the Url
        spec.pathParams("first", "user", "second", "yudem2929");

        //Set the expected data
        Map<String, Object> expectedData = obj.expectedDataMapDelete("yudem2929");

        //Send the Request and delete the Response
        Response response = given().spec(spec).when().delete("/{first}/{second}");

        //Do Assertion
        HashMap actualData = response.as(HashMap.class);
        System.out.println("expectedData = " + expectedData);
        System.out.println("actualData = " + actualData);
        assertEquals(expectedData.get("message"), actualData.get("message"));

    }
}
