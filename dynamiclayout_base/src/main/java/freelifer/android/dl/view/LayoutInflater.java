package freelifer.android.dl.view;

import android.content.Context;
import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kzhu on 2019/8/24.
 */
public class LayoutInflater {

    public LayoutInflater() {
    }

    public static LayoutInflater from() {
        return new LayoutInflater();
    }


    public View inflate(Context context, String json) {
        try {
            return inflate(context, new JSONObject(json));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public View inflate(Context context, JSONObject jsonObject) {
        return inflate(context, new AttributeSet(jsonObject));
    }

    public View inflate(Context context, AttributeSet attrs) {
        final View temp = createViewFromType(context, attrs);
//        ViewGroup.LayoutParams params = null;

        rInflateChildren(context, attrs, temp);
        return temp;
    }

    private void rInflateChildren(final Context context, AttributeSet attrs, final View parent) {
        if (!(parent instanceof ViewGroup)) {
            return;
        }
        attrs.eachJSONArray("children", new AttributeSet.Action<AttributeSet>() {
            @Override
            public void call(AttributeSet attrs2) {
                final View view = createViewFromType(context, attrs2);
                ViewGroup viewGroup = (ViewGroup) parent;
                ViewGroup.LayoutParams params = viewGroup.generateLayoutParams();
                rInflateChildren(context, attrs2, view);
                viewGroup.addView(view, params);
            }
        });
    }

    private View createViewFromType(Context context, AttributeSet attrs) {
        return create(context, attrs.optString("type", ""), attrs);
    }

    private static final Map<String, ViewConverter> viewConverters = new HashMap<>();

    public static View create(Context context, String type, AttributeSet attrs) {
        ViewConverter converter = viewConverters.get(type);
        if (converter == null) {
            return null;
        }
        return converter.getView(context, attrs);
    }

    public static boolean addViewConverter(@NonNull ViewConverter converter) {
        if (viewConverters.containsKey(converter.getType())) {
            return false;
        }
        viewConverters.put(converter.getType(), converter);
        return true;
    }

}
