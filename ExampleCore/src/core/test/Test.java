package core.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Test {
	
	public String stringReturn() {
		JSONObject obj = new JSONObject();
        obj.put("name", "mkyong.com");
        obj.put("age", 100);

        JSONArray list = new JSONArray();
        list.add("msg 1");
        list.add("msg 2");
        list.add("msg 3");

        obj.put("messages", list);

		return(obj.toJSONString());
	}
}

