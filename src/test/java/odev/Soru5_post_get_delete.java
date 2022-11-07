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
import static org.junit.Assert.assertTrue;

public class Soru5_post_get_delete extends PetstoreSwaggerBaseUrl {

     /*
    https://petstore.swagger.io/ documantation adresini kullanarak 'user' oluşturan ve oluşsan bu user'ı silen bir otomasyon kodu yazınız.
    */

    PetstoreSwaggerTestData obj = new PetstoreSwaggerTestData();
    Map<String, Object> expectedData = obj.expectedDataMap(2929, "yudem2929", "yusuf", "demir", "ydydydydyd123@gmail.com"
            , "123456ydydyd", "0123456789", 3434);

    @Test
    public void postUser() {

        // Set the Url
        spec.pathParam("1", "user");

        // Set the expected Data
        System.out.println("expectedData = " + expectedData);

        // Send the Request and Post the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{1}");
        response.prettyPrint();

        // Do Assertion
        HashMap actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals( String.valueOf(expectedData.get("id")), actualData.get("message"));

    }

    @Test
    public void get() {

        //Set the Url
        spec.pathParams("1", "user", "second", expectedData.get("username"));

        //Set the expected data
        System.out.println("expectedData = " + expectedData);

        //Send the Request and Get the Response
        Response response = given().spec(spec).contentType(ContentType.JSON).when().get("/{1}/{second}");
        response.prettyPrint();

        // Do Assertion
        HashMap actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.get("id"),actualData.get("id"));
        assertEquals(expectedData.get("firstName"),actualData.get("firstName"));
        assertEquals(expectedData.get("lastName"),actualData.get("lastName"));
        assertEquals(expectedData.get("password"),actualData.get("password"));
        assertEquals(expectedData.get("userStatus"),actualData.get("userStatus"));
        assertEquals(expectedData.get("phone"),actualData.get("phone"));
        assertEquals(expectedData.get("email"),actualData.get("email"));
        assertEquals(expectedData.get("username"),actualData.get("username"));

    }


    @Test
    public void deleteUser() {

        //Set the Url
        spec.pathParams("first", "user", "second", "yudem2929");

        //Set the expected data
        Map<String, Object> expectedData = obj.expectedDataMapDelete("yudem2929");

        //Send the Request and Get the Response
        Response response = given().spec(spec).when().delete("/{first}/{second}");

        //Do Assertion
        HashMap actualData = response.as(HashMap.class);
        System.out.println("expectedData = " + expectedData);
        System.out.println("actualData = " + actualData);

        //1. Yol
        assertEquals(expectedData.get("message"), actualData.get("message"));

    }
}
