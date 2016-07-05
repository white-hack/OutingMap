package com.hack.white.outingmap.common;

import org.json.JSONArray;
import org.json.JSONException;

public class MapUtil {
    public static String getCategory(JSONArray category) throws JSONException {
        if (category.length() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < category.length(); i++) {
            sb.append(category.getString(i)).append(",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
}
