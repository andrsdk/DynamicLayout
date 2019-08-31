package freelifer.android.dl.framework;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import freelifer.android.dl.view.AttributeSet;

import static freelifer.android.dl.framework.LayoutDataInflater.Coverters.getConverter;

/**
 * @author kzhu on 2019/8/24.
 */
public class ViewGroupConverter extends ViewConverter {

    @Override
    public String getType() {
        return "viewGroup";
    }

    @Override
    public ViewGroupPOJO getViewPOJO(AttributeSet attrs) {
        return new ViewGroupPOJO(attrs);
    }

    @Override
    public View getView(Context context) {
        return null;
    }

    @Override
    public void onRender(View child, ViewGroup viewGroup, ViewPOJO viewPOJO) {
        super.onRender(child, viewGroup, viewPOJO);

        ViewGroup vg = (ViewGroup) child;
        ViewGroupPOJO viewGroupPOJO = (ViewGroupPOJO) viewPOJO;
        final int count = viewGroupPOJO.getChildrenCount();
        final ViewPOJO[] children = viewGroupPOJO.getChildren();

        for (int i = 0; i < count; i++) {
            final ViewPOJO child2 = children[i];

            IConverter converter = getConverter(child2.getType());
            converter.render(vg, child2);
        }
    }
}
