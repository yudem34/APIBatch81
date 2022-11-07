package get_requests;

import base_url.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        1-) The value of "pagination limit" is 10 -> TR : "Sayfalandırma sınırı" değeri 10'dur
    And
        2-) The "current link" should be "https://gorest.co.in/public/v1/users?page=1" -> TR : "Geçerli bağlantı" olmalıdır
    And
        3-) The number of users should  be 10 -> TR : Kullanıcı sayısı 10 olmalıdır
    And
        4-) We have at least one "active" status -> TR : En az bir "active" durumumuz var
    And
        5-) Sujata Chaturvedi, Navin Panicker and Bhadran Mehra LLD are among the users -> TR : Niranjan Gupta, Sameer Namboothiri ve Gandharva çağrısı kullanıcılar arasında
    And
        6-) The female users are less than or equals to male users -> TR : Kadın kullanıcılar erkek kullanıcılara eşit veya daha az
    */

    @Test
    public void get01() {
        //1. Set The URL
        spec.pathParam("first","users");
        // 2. Set The Expected Data ( put, post, patch)

        // 3. Send The Request And Get The Response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // 4. Do Assertion
        // Body (response) ile
        response.
                then().
                assertThat().
                statusCode(200).
                body("meta.pagination.limit",equalTo(10),
                        "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data",hasSize(10),
                        "data.status",hasItem("active"),
                        "data.name",hasItems("Rudra Tandon","Amodini Rana DC","Lavanya Adiga"));

        List<String> female=response.jsonPath().getList("data.findAll{it.gender=='female'}.gender");
        List<String> male=response.jsonPath().getList("data.findAll{it.gender=='male'}.gender");
        assertTrue(female.size()<=male.size());

        // Json Path ile
        JsonPath jsonPath=response.jsonPath();
        // 1-) The value of "pagination limit" is 10 -> TR : "Sayfalandırma sınırı" değeri 10'dur
        assertEquals(10,jsonPath.getInt("meta.pagination.limit"));

        // 2-) The "current link" should be "https://gorest.co.in/public/v1/users?page=1" -> TR : "Geçerli bağlantı" olmalıdır
        assertEquals("https://gorest.co.in/public/v1/users?page=1",jsonPath.getString("meta.pagination.links.current")); // 2

        // 3-) The number of users should  be 10 -> TR : Kullanıcı sayısı 10 olmalıdır
        List<Integer> idList=jsonPath.getList("data.findAll{it.id}.id");
        assertEquals(10,idList.size());

        // 4-) We have at least one "active" status -> TR : En az bir "active" durumumuz var
        List<String> statusList=jsonPath.getList("data.findAll{it.status}.status");
        assertTrue(statusList.contains("active"));

        // 5-) Sujata Chaturvedi, Navin Panicker and Bhadran Mehra LLD are among the users -> TR : Kullanicilar arasinda bu isimler olmali
        List<String> nameList=jsonPath.getList("data.findAll{it.name}.name");

        /* 1. Yol */
        assertTrue(nameList.contains("Rudra Tandon") && nameList.contains("Amodini Rana DC") && nameList.contains("Lavanya Adiga"));

        /* 2. Yol dinamik */
        String [] expectedData= {"Rudra Tandon", "Amodini Rana DC", "Lavanya Adiga"};
        int sayac=0;
        for (int i = 0; i <nameList.size() ; i++) {
            for (int j = 0; j <expectedData.length ; j++) {
                if (nameList.get(i).equals(expectedData[j])){
                    sayac++;
                }
            }
        }
        assertEquals(sayac,expectedData.length);

        // 6-) The female users are less than or equals to male users -> TR : Kadın kullanıcılar erkek kullanıcılara eşit veya daha az
        List<String> femaleList=jsonPath.getList("data.findAll{it.gender=='female'}.gender");
        List<String> maleList=jsonPath.getList("data.findAll{it.gender=='male'}.gender");
        assertTrue(femaleList.size()<=maleList.size());




    }
}
