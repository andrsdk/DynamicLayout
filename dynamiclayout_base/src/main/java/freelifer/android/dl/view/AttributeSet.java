package freelifer.android.dl.view;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import freelifer.android.dl.common.Kits;

/**
 * @author kzhu on 2019/8/23.
 */
public class AttributeSet {

    private JSONObject jsonObject;

    public AttributeSet(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String optString(String key, String defaultValue) {
        return jsonObject.optString(key, defaultValue);
    }

    public int getColor(String key) {
        return Kits.getColor(jsonObject.optString(key));
    }

    public int getSize(Context context, String key, int defaultValue) {
        String value = jsonObject.optString(key);
        int size = Kits.getSize(context, value);
        return size == 0 ? defaultValue : size;
    }

    public int optInt(String key, int defaultValue) {
        return jsonObject.optInt(key, defaultValue);
    }

    public void eachJSONArray(String key, Action<AttributeSet> action) {
        if (jsonObject == null) {
            return;
        }
        JSONArray jsonArray = jsonObject.optJSONArray(key);
        if (jsonArray == null) {
            return;
        }
        int len = jsonArray.length();
        if (len <= 0) {
            return;
        }

        for (int i = 0; i < len; i++) {
            JSONObject obj = jsonArray.optJSONObject(i);
            if (obj == null) {
                continue;
            }
            action.call(new AttributeSet(obj));
        }
    }

    public interface Action<T> {
        void call(T t);
    }

}
