package jp.mixi.practice.serializable;

import java.util.Calendar;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.format.DateFormat;

public class NetworkClient {

    private Random rand = new Random();

    public String getUser(int userId) {
        return createUser(userId).toString();
    }

    public String getFriends() {
        JSONArray array = new JSONArray();
        for (int i = 0; i < 10; i++) {
            array.put(createUser(rand.nextInt(10000)));
        }
        return array.toString();
    }

    private JSONObject createUser(int userId) {
        try {
            JSONObject json = new JSONObject();
            json.put("id", userId);
            json.put("name", "user_" + userId);
            if (rand.nextBoolean()) {
                json.put("age", rand.nextInt(100));
            }
            if (rand.nextBoolean()) {
                json.put("keyword", "keyword_" + userId);
            }
            CharSequence postedTime = DateFormat.format("yyyy-MM-dd hh:mm:ss",
                    getPastedTime(-1 * rand.nextInt(100)));
            JSONObject status = new JSONObject();
            status.put("postedTime", postedTime);
            status.put("text", "しぶやなう");
            json.put("status", status);
            return json;
        } catch (JSONException e) {
            return null;
        }
    }
    private Calendar getPastedTime(int addDate){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, addDate);
        return cal;
    }

}
