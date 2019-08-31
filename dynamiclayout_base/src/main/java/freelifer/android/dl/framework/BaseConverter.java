package freelifer.android.dl.framework;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author kzhu on 2019/8/31.
 */
public abstract class BaseConverter implements IConverter {

    @Override
    public void render(ViewGroup viewGroup, ViewPOJO viewPOJO) {
        View view = getView(viewGroup.getContext());
        bind(view, viewPOJO);

        onRender(view, viewGroup, viewPOJO);
    }

    public void onRender(View child, ViewGroup viewGroup, ViewPOJO viewPOJO) {
        //
    }

    public void bind(View view, ViewPOJO viewPOJO) {
        if (view == null || viewPOJO == null) {
            return;
        }

//        int left, int top, int right, int bottom
        view.setPadding(viewPOJO.getPaddingLeft(), viewPOJO.getPaddingTop(), viewPOJO.getPaddingRight(), viewPOJO.getPaddingBottom());
        view.setBackgroundColor(viewPOJO.getBackground());
    }

    public abstract View getView(Context context);
}
