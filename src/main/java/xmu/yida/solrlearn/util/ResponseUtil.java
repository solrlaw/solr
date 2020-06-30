package xmu.yida.solrlearn.util;


import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    public static Object ok() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", 0);
        obj.put("message", "成功");
        return obj;
    }

    public static Object ok(Object data) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", 0);
        obj.put("message", "成功");
        obj.put("data", data);
        return obj;
    }

    public static Object fail() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", -1);
        obj.put("message", "错误");
        return obj;
    }

    public static Object customization(int code, String message) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", code);
        obj.put("message", message);
        return obj;
    }

    public static Object customization(int code, String message, Object data){
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", code);
        obj.put("message", message);
        obj.put("data", data);
        return obj;
    }
}
