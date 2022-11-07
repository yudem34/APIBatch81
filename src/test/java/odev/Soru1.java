package odev;

import base_url.AutomationExerciseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Soru1 extends AutomationExerciseUrl {
     /*
        Given
            https://automationexercise.com/api/brandsList
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be "text/html; charset=utf-8"
        And
            Status Line should be HTTP/1.1 200 OK
        And
             Number of H&M brands must be equal to Polo(H&M marka sayısı Polo marka sayısına eşit olmalı)
*/


    @Test
    public void get01() {
        // 1. Set The Url
        spec.pathParam("first", "brandsList");

        // 2. Set The expected Data

        // 3. Send The request And get The Response
        Response response = given().spec(spec).when().get("/{first}");
        JsonPath jsonPath=response.jsonPath();
        // jsonPath.prettyPrint();

        // 4. Do Assertion
        List<String> HMList=jsonPath.getList("brands.findAll{it.brand='H&M'}.brand");
        List<String> poloList=jsonPath.getList("brands.findAll{it.brand='Polo'}.brand");
        response.
                then().
                assertThat().statusCode(200).contentType("text/html; charset=utf-8")
                        .statusLine("HTTP/1.1 200 OK");
        assertEquals(HMList.size(),poloList.size());
    }

}
