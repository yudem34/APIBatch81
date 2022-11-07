package get_requests;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15 extends RestfulBaseUrl {
    /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
           {
                "firstname": "Oliver",
                "lastname": "Smith",
                "totalprice": 100,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2022-07-18",
                    "checkout": "2022-07-19"
                },
                "additionalneeds": "Breakfast"
            }
     */

    @Test
    public void get15() {
        spec.pathParams("first", "booking", "second", 22);

        // 2) set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingPojo expectedData = new BookingPojo("Guoqiang", "Morante Briones", 111, true, bookingDatesPojo, "Breakfast");

        // 3) send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        // 4) do the assertion
        // Hard Assertion
        BookingPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), BookingPojo.class);
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

        assertEquals(bookingDatesPojo.getCheckin(), expectedData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), expectedData.getBookingdates().getCheckout());

        // Soft Assertion


    }
}

