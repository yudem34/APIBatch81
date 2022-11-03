package test_data;

import java.util.HashMap;
import java.util.Map;

public class GetRequest03Data {
    public Map<String,Object> expectedData(Integer id, String email, String first_name,String last_name,String avatar ){

        Map<String,Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("id",id);
        expectedDataMap.put("email",email);
        expectedDataMap.put("first_name",first_name);
        expectedDataMap.put("last_name",last_name);
        expectedDataMap.put("avatar",avatar);

        return expectedDataMap;
    }
}