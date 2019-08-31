package freelifer.android.dl.framework;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import freelifer.android.dl.view.AttributeSet;

/**
 * @author kzhu on 2019/8/24.
 */
public class ViewConverter implements IConverter {

    @Override
    public String getType() {
        return "view";
    }

    @Override
    public ViewPOJO getViewPOJO(AttributeSet attrs) {
        return new ViewPOJO(attrs);
    }

    @Override
    public void render(ViewGroup viewGroup, ViewPOJO viewPOJO) {
        View view = getView(viewGroup.getContext());

        onRender(view, viewGroup, viewPOJO);

        ViewGroupPOJO.LayoutParams lp = viewPOJO.getLayoutParams();
        ViewGroup.LayoutParams layoutParams = onGenerateLayoutParams(lp);

        viewGroup.addView(view, layoutParams);
    }

    public void onRender(View child, ViewGroup viewGroup, ViewPOJO viewPOJO) {
        if (child == null || viewPOJO == null) {
            return;
        }

//        int left, int top, int right, int bottom
        child.setPadding(viewPOJO.getPaddingLeft(), viewPOJO.getPaddingTop(), viewPOJO.getPaddingRight(), viewPOJO.getPaddingBottom());
        child.setBackgroundColor(viewPOJO.getBackground());
    }

    public View getView(Context context) {
        return new View(context);
    }

    public ViewGroup.LayoutParams onGenerateLayoutParams(ViewGroupPOJO.LayoutParams lp) {
        return new ViewGroup.LayoutParams(lp.getWidth(), lp.getHeight());
    }
}
