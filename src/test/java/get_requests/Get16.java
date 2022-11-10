package get_requests;

import base_url.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get16 extends DummyRestApiBaseUrl {

    /*
    
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language

           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */
    
    /*
    Given 
        https://dummy.restapiexample.com/api/v1/employees
    When
        User sends Get request
    Then
        Status code is 200
    And
        i) Status code is 200
       ii) There are 24 employees / 24 calisan var
      iii) "Tiger Nixon" and "Garrett Winters" are among the employees / calisanlar arsinda var mi
       iv) The greatest age is 66 / en buyuk 66 / en kucuk isim Tatyana Fitzpatrick
        v) The name of the lowest age is "Tatyana Fitzpatrick" / Toplam tüm çalışanların maaşı 6.644.770
       vi) Total salary of all employees is 6,644,770    
    */


    @Test
    public void get16() {
        spec.pathParam("first","employees");
        Response response=given().spec(spec).contentType(ContentType.JSON).when().get("/{first}");
        response.prettyPrint();

        response.then().assertThat().body("data.id",hasSize(24)); // 24 Calisan var
        response.then().assertThat().body("data.employee_name",hasItems("Tiger Nixon","Garrett Winters")); // "Tiger Nixon","Garrett Winters" calısanlarda var mı

        List<Integer> ageList=response.jsonPath().getList("data.employee_age");
        Collections.sort(ageList);
        assertEquals((Integer)66,ageList.get(ageList.size()-1)); // En buyuk yas 66

        //The name of the lowest age is "Tatyana Fitzpatrick"
        List<String> lowestAgedEmployee= response.jsonPath().getList("data.findAll{it.employee_age == "+ageList.get(0)+"}.employee_name");
        assertEquals("Tatyana Fitzpatrick",lowestAgedEmployee.get(0));

        //Total salary of all employees is 6,644,770
        List<Integer> salaries = response.jsonPath().getList("data.employee_salary");
        System.out.println("salaries = " + salaries);

        //1. Yol
        int sum = 0;
        for (int w:salaries){

            sum +=w;

        }
        System.out.println("sum = " + sum);

        assertEquals(6644770,sum);

        //2. Yol
        int sum2 = salaries.stream().reduce(0, Integer::sum);
        System.out.println("sum2 = " + sum2);
        assertEquals(6644770,sum2);

        //3. Yol
        int sum3 = salaries.stream().reduce(0, Math::addExact);
        System.out.println("sum3 = " + sum3);
        assertEquals(6644770,sum3);

    }
}
