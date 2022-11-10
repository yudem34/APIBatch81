package Practice;

import base_url.PetstoreSwaggerBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.ReqresPojo;
import test_data.ReqresTestData;
import utils.ObjectMapperUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class API_Test_Kalibi extends PetstoreSwaggerBaseUrl {
    @Test
    public void name() throws IOException {

        // Set the Url
        // Set the expected Data
        // Send the Request and get the response
        // Do Assertion

        /* Set the Url */
        // Tek parametre icin pathParam kullanilir
        spec.pathParam("first", "user");
        // Coklu parametre icin pathParams kullanilir
        spec.pathParams("first","user","second",102);

        /* Set the expected Data (patch,put ve post)*/
        // Sadece dogrulama icinse test bu kisma gerek yok !
        // Veri gondermek icin

        // 1.Yol test_data package'dan Map ile
        ReqresTestData obj=new ReqresTestData();
        Map<String, String> expectedData = obj.dataKeyMap("Joseph","Iron");
        System.out.println("expectedData = " + expectedData);

        // 2.Yol pojos package'dan Pojo ile
        ReqresPojo expectedData2=new ReqresPojo("Joseph","Iron");
        System.out.println("expectedData2 = " + expectedData2);

        // 3.Yol objectMapper_Map
        String jsonString=obj.dataKeyString("Joseph","Iron");
        Map<String, Object> expectedData3 = new ObjectMapper().readValue(jsonString, HashMap.class);
        System.out.println("expectedData3: " + expectedData3);


        /* Send the Request and post,put ve patch the response */

        // i-) post,patch veya put islemlerinde veri gondermek icin bod() kullanilir
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}"); // Tek parametre degilse "/{}/{}" seklinde devam eder
        response.prettyPrint();

        // ii-) get islemlerinde body kullanilmaz
        Response response2 = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}");
        response2.prettyPrint();

        /* Do Assertion */
        /* Gson ile dogrulama */
        Gson gson=new Gson();
        response.then().statusCode(200);

        // 1.Yol icin Map ile actualData olusturma ve dogrulama
        Map<String,Object> actualData=gson.fromJson(response.asString(), HashMap.class);
        System.out.println("actualData="+actualData);
        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));

        // 2.Yol icin Pojo ile actualData olusturma ve dogrulama
        ReqresPojo actualData2 = gson.fromJson(response.asString(), ReqresPojo.class);
        System.out.println("actualData2 = " + actualData2);
        assertEquals(expectedData2.getName(),actualData2.getName());
        assertEquals(expectedData2.getJob(),actualData2.getJob());

        /* Object Mapper Map ile dogrulama*/
        // 3.Yol icin ObjectMapper Map ile actualData olusturma ve dogrulama
        HashMap actualData3 = new ObjectMapper().readValue(response.asString(), HashMap.class);
        System.out.println("actualData3="+actualData3);
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.get("name"),actualData.get("name"));

        // 2.Yol icin ObjectMapper Pojo ile actualData olusturma ve dogrulama
        ReqresPojo actualData4 = ObjectMapperUtils.convertJsonToJava(response.asString(), ReqresPojo.class);
        System.out.println("actualData4 = " + actualData4);
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData2.getName(),actualData4.getName());
        assertEquals(expectedData2.getJob(),actualData4.getJob());

        /* 1) JUnit Assert ile */
        assertEquals(200,response.getStatusCode());
        assertEquals("HTTP/1.1 200 ",response.getStatusLine());
        assertEquals("application/json",response.getContentType());

        /* 2) assertThat ile */
        response.then().assertThat().
                statusCode(200).
                statusLine("HTTP/1.1 200 ").
                contentType(ContentType.JSON);


        /* BODY TEST ICIN */
        /* Matcher Class ile */
        response.then().assertThat().body("id",equalTo(42697),
                "firstName",equalTo("Ali"),
                "lastName",equalTo("Deckow"),
                "middleInitial",equalTo("Leroy Hoeger Sipes"),
                "email",equalTo("com.github.javafaker.Name@7c011174@gmail.com"),
                "mobilePhoneNumber",equalTo("115-471-7051"),
                "phoneNumber",equalTo("876-394-6968"),
                "zipCode",equalTo("67321"),
                "address",equalTo("I live in St louis MO"),
                "city",equalTo("St Louis"),
                "ssn",equalTo("473-22-1798"),
                "createDate",equalTo("0211-09-09T05:50:36Z"),
                "zelleEnrolled",equalTo(false),
                "country",equalTo(null),
                "state",equalTo(""),
                "user",equalTo(null),
                "accounts",equalTo(Arrays.asList()));

        /* JsonPath ile */
        JsonPath json = response.jsonPath();
        assertEquals(42697,json.getInt("id"));
        assertEquals("Ali",json.getString("firstName"));
        assertEquals("Deckow",json.getString("lastName"));
        assertEquals("Leroy Hoeger Sipes",json.getString("middleInitial"));
        assertEquals("com.github.javafaker.Name@7c011174@gmail.com",json.getString("email"));
        assertEquals("115-471-7051",json.getString("mobilePhoneNumber"));
        assertEquals("876-394-6968",json.getString("phoneNumber"));
        assertEquals("67321",json.getString("zipCode"));
        assertEquals("I live in St louis MO",json.getString("address"));
        assertEquals("St Louis",json.getString("city"));
        assertEquals("473-22-1798",json.getString("ssn"));
        assertEquals("0211-09-09T05:50:36Z",json.getString("createDate"));
        assertEquals(false, json.getBoolean("zelleEnrolled"));
        assertEquals(null,json.getString("country"));
        assertEquals("",json.getString("state"));
        assertEquals(null,json.getString("user"));
        assertEquals("[]",json.getString("accounts"));
    }
}

