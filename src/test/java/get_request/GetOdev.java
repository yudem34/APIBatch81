package get_request;

import base_url.AutomationExerciseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetOdev extends AutomationExerciseUrl {

            /*
        Given
            https://automationexercise.com/api/productsList
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be "text/html; charset=utf-8"
        And
            Status Line should be HTTP/1.1 200 OK
        And
             There must be 12 Women, 9 Men, 13 Kids usertype in products
          */

    @Test
    public void get01() {

        // Set the Url
        spec.pathParam("first", "productsList");
        // Set The Expected Data
        // Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}");
        // Do Assertion
        JsonPath jsonPath = response.jsonPath();
        /*jsonPath.prettyPrint();*/
        // HTTP Status Code should be 200
        // Content Type should be "text/html; charset=utf-8"
        response.then().assertThat().statusCode(200).contentType("text/html; charset=utf-8")
                .statusLine("HTTP/1.1 200 OK");
        // There must be 12 Women, 9 Men, 13 Kids usertype in products
        List<String> totalList = jsonPath.getList("products.findAll{it.category}category.usertype.usertype");
        List<String> women = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
        List<String> men = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
        List<String> kids = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");
        assertEquals(12, women.size());
        assertEquals(9, men.size());
        assertEquals(13, kids.size());

    }
}
