package test_data;

import java.util.HashMap;
import java.util.Map;

public class RestfulTestData {

    public Map<String,String> bookingdatesMap(String checkin, String checkout){

        Map<String, String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", checkin);
        bookingdatesMap.put("checkout", checkout);

        return bookingdatesMap;
    }

    public Map<String,Object> expectedDataMap(String firstname, String lastname, Integer totalprice,Boolean depositpaid,Map<String,String> bookingdatesMap){

        Map<String,Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname",firstname);
        expectedDataMap.put("lastname",lastname);
        expectedDataMap.put("totalprice",totalprice);
        expectedDataMap.put("depositpaid",depositpaid);
        expectedDataMap.put("bookingdates", bookingdatesMap);

        return expectedDataMap;
    }
}

/*{
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }*/
