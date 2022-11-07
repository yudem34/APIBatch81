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

public class Soru5 extends PetstoreSwaggerBaseUrl {

     /*
    https://petstore.swagger.io/ documantation adresini kullanarak 'user' oluşturan ve oluşsan bu user'ı silen bir otomasyon kodu yazınız.
    */

    PetstoreSwaggerTestData obj=new PetstoreSwaggerTestData();
    @Test
    public void postUser() {

        // Set the Url
        spec.pathParam("1", "user");

        // Set the expected Data

        Map<String, Object> expectedData = obj.expectedDataMap(2929, "yudem2929", "yusuf","demir","ydydydydyd123@gmail.com"
                ,"123456ydydyd","0123456789",3434);

        // Send the Request and Post the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{1}");
        response.prettyPrint();
        spec.pathParams("1","user","second",expectedData.get("username"));
        Response response2=given().spec(spec).contentType(ContentType.JSON).when().get("/{1}/{second}");
        response2.prettyPrint();


        // Do Assertion
        Map<String ,Object> actualData=response2.as(HashMap.class);
        System.out.println(expectedData);
        System.out.println(actualData);

        assertEquals(expectedData,actualData);

    }

    @Test
    public void deleteUser() {
        //Set the Url
        spec.pathParams("first","user","second","yudem2929");

        //Set the expected data
        Map<String,Object> expectedData = obj.expectedDataMapDelete(200,"unknown","yudem2929");

        //Send the Request and Get the Response
        Response response = given().spec(spec).when().delete("/{first}/{second}");

        //Do Assertion
        Map<String ,Object> actualData=response.as(HashMap.class);
        System.out.println("expectedData = " + expectedData);
        System.out.println("actualData = " + actualData);
        //1. Yol
        assertEquals(expectedData, actualData);

    }
}
