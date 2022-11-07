package test_data;

import java.util.HashMap;
import java.util.Map;

public class PetstoreSwaggerTestData {

    public Map<String, Object> expectedDataMap(Integer id, String username, String firstName, String lastName, String email, String password, String phone, Integer userStatus) {

        Map<String, Object> expectedDataMap = new HashMap<>();

        if (id != null) {
            expectedDataMap.put("id", id);
        }
        if (username != null) {
            expectedDataMap.put("username", username);
        }
        if (firstName != null) {
            expectedDataMap.put("firstName", firstName);
        }
        if (lastName != null) {
            expectedDataMap.put("lastName", lastName);
        }
        if (email != null) {
            expectedDataMap.put("email", email);
        }
        if (password != null) {
            expectedDataMap.put("password", password);
        }
        if (phone != null) {
            expectedDataMap.put("phone", phone);
        }
        if (userStatus != null) {
            expectedDataMap.put("userStatus", userStatus);
        }

        return expectedDataMap;
    }

    public Map<String, Object> expectedDataMapDelete(Integer code, String type, String message) {

        Map<String, Object> expectedDataMapDelete = new HashMap<>();

        if (code != null) {
            expectedDataMapDelete.put("code", code);
        }
        if (type != null) {
            expectedDataMapDelete.put("type", type);
        }
        if (message != null) {
            expectedDataMapDelete.put("message", message);
        }

        return expectedDataMapDelete;
    }
}

