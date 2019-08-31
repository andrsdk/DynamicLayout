package freelifer.android.dl.framework;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import freelifer.android.dl.view.AttributeSet;

import static freelifer.android.dl.framework.LayoutDataInflater.Coverters.createViewPOJO;
import static freelifer.android.dl.framework.LayoutDataInflater.Coverters.getConverter;

/**
 * @author kzhu on 2019/8/31.
 */
public class LayoutDataInflater {

    public static class Coverters {

        private static final Map<String, IConverter> viewConverters = new HashMap<>();

        static {
            addViewConverter(new ViewConverter());
        }

        public static ViewPOJO createViewPOJO(String type, AttributeSet attrs) {
            IConverter converter = viewConverters.get(type);
            if (converter == null) {
                return null;
            }
            return converter.getViewPOJO(attrs);
        }

        public static IConverter getConverter(String type) {
            return viewConverters.get(type);
        }

        public static boolean addViewConverter(@NonNull IConverter converter) {
            if (viewConverters.containsKey(converter.getType())) {
                return false;
            }
            viewConverters.put(converter.getType(), converter);
            return true;
        }

    }

    private Context context;
    private ViewPOJO viewPOJO;

    public static LayoutDataInflater from(Context context) {
        return new LayoutDataInflater(context);
    }

    private LayoutDataInflater(Context context) {
        this.context = context.getApplicationContext();
    }

    public LayoutDataInflater inflate(String json) {
        try {
            this.viewPOJO = inflate(new JSONObject(json));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void render(ViewGroup viewGroup) {
        if (viewPOJO == null) {
            return;
        }
        IConverter converter = getConverter(viewPOJO.getType());
        converter.render(viewGroup, viewPOJO);
    }

    public ViewPOJO inflate(JSONObject jsonObject) {
        return inflate(new AttributeSet(context, jsonObject));
    }

    public ViewPOJO inflate(AttributeSet attrs) {
        final ViewPOJO temp = createViewFromType(attrs);
        rInflateChildren(attrs, temp);
        return temp;
    }

    private void rInflateChildren(AttributeSet attrs, final ViewPOJO parent) {
        if (!(parent instanceof ViewGroupPOJO)) {
            return;
        }
        attrs.eachJSONArray1("children", new AttributeSet.Action<JSONObject>() {
            @Override
            public void call(JSONObject json) {
                AttributeSet attrs2 = new AttributeSet(context, json);
                final ViewPOJO view = createViewFromType(attrs2);
                ViewGroupPOJO viewGroup = (ViewGroupPOJO) parent;
                ViewGroupPOJO.LayoutParams params = viewGroup.generateLayoutParams(attrs2);
                rInflateChildren(attrs2, view);
                viewGroup.addViewPOJO(view, params);
            }
        });
    }

    private ViewPOJO createViewFromType(AttributeSet attrs) {
        return createViewPOJO(attrs.optString("type", ""), attrs);
    }

}
