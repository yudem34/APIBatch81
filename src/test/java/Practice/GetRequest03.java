package Practice;

import base_url.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GetRequest03Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class GetRequest03 extends ReqresBaseUrl {

    @Test
    public void test01() {
        spec.pathParams("first","users");
        GetRequest03Data obj=new GetRequest03Data();

        Map<String,Object> supportMap=new HashMap<>();
        supportMap.put("url","https://reqres.in/#support-heading");
        supportMap.put("text","To keep ReqRes free, contributions towards server costs are appreciated!");
        Map<String,Object> expectedData=obj.expectedData(1,"george.bluth@reqres.in","George",
                "Bluth","https://reqres.in/img/faces/1-image.jpg");
        expectedData.put("support",supportMap);

        Response response = given().spec(spec).when().get("/{first}");
        //response.prettyPrint();
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("expectedData = " + expectedData);
        System.out.println("actualData = " + actualData);

        // ((Map)((List)actualData.get("data")).get(0)).get("id")
        assertEquals(expectedData.get("id"),(((Map)((List)actualData.get("data")).get(0)).get("id")));
        assertEquals(expectedData.get("email"),(((Map)((List)actualData.get("data")).get(0)).get("email")));
        assertEquals(expectedData.get("first_name"),(((Map)((List)actualData.get("data")).get(0)).get("first_name")));
        assertEquals(expectedData.get("last_name"),(((Map)((List)actualData.get("data")).get(0)).get("last_name")));
        assertEquals(expectedData.get("avatar"),(((Map)((List)actualData.get("data")).get(0)).get("avatar")));


    }
}
