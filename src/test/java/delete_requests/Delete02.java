package delete_requests;

import base_url.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDeletePojo;
import utils.ObjectMapperUtils;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Delete02 extends DummyRestApiBaseUrl {
    /*
     URL: https://dummy.restapiexample.com/api/v1/delete/2
     HTTP Request Method: DELETE Request
     Test Case: Type by using Gherkin Language
     Assert:     i) Status code is 200
                 ii) "status" is "success"
                 iii) "data" is "2"
                 iv) "message" is "Successfully! Record has been deleted"
    */

  /*
    Test Case: Type by using Gherkin Language
    Given
        URL: https://dummy.restapiexample.com/api/v1/delete/2
    When
        HTTP Request Method: DELETE Request
    Then
        i) Status code is 200
    And
        ii) "status" is "success"
    And
        iii) "data" is "2"
    And
        iv) "message" is "Successfully! Record has been deleted" */

    @Test
    public void delete02() {
        spec.pathParams("first","delete","second",2);

        DummyRestApiDeletePojo expectedData=new DummyRestApiDeletePojo("success","2","Successfully! Record has been deleted");

        Response response = given().spec(spec).when().delete("/{first}/{second}");
        //response.prettyPrint();

        //DummyRestApiDeletePojo actualData=response.as(DummyRestApiDeletePojo.class);
        DummyRestApiDeletePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),DummyRestApiDeletePojo.class);
        assertEquals(200,response.getStatusCode());

        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getData(),actualData.getData());
        assertEquals(expectedData.getMessage(),actualData.getMessage());
        assertEquals(expectedData.toString(),actualData.toString());

    }
}
