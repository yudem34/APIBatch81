package test_data;

import java.util.HashMap;
import java.util.Map;

public class ReqresTestData {

        public Map<String, String> dataKeyMap(String name, String job) {

        Map<String, String> dataKeyMap = new HashMap<>();

        if (name != null){
        dataKeyMap.put("name",name);
        }
        if (job != null) {
            dataKeyMap.put("job", job);
        }

        return dataKeyMap;
    }

        public String dataKeyString(String name,String job){
            String dataStr="{\n" +
                    "\"name\": \""+name+"\",\n" +
                    "\"job\": \""+job+"\"\n" +
                    "}";
            return dataStr;
        }

}
