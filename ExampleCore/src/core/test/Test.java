package core.test;

import org.json.simple.*;

public class Test {
	
	public String stringReturn() {
		JSONObject obj = new JSONObject();
        obj.put("group", "2 PDE and Gradle");
        obj.put("Sprint", 2);

        JSONArray list = new JSONArray();
        list.add("Felix");
        list.add("Phil");
        list.add("Paul");
        list.add("Eike");

        obj.put("members", list);

		return(obj.toJSONString());
	}
}

