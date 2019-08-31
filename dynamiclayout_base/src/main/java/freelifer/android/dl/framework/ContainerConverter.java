package freelifer.android.dl.framework;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;

import freelifer.android.dl.view.AttributeSet;

/**
 * @author kzhu on 2019/8/24.
 */
public class ContainerConverter extends ViewGroupConverter {

    @Override
    public String getType() {
        return "container";
    }

    @Override
    public ContainerPOJO getViewPOJO(AttributeSet attrs) {
        return new ContainerPOJO(attrs);
    }

    @Override
    public View getView(Context context) {
        return new FlexboxLayout(context);
    }

    @Override
    public void onRender(View child, ViewGroup viewGroup, ViewPOJO viewPOJO) {
        super.onRender(child, viewGroup, viewPOJO);
        ContainerPOJO containerPOJO = (ContainerPOJO) viewPOJO;
        FlexboxLayout flexboxLayout = (FlexboxLayout) child;

        flexboxLayout.setFlexDirection(flexDirection(containerPOJO.getFlexDirection()));
        flexboxLayout.setFlexWrap(flexWrap(containerPOJO.getFlexWrap()));
    }

    @Override
    public ViewGroup.LayoutParams onGenerateLayoutParams(ViewGroupPOJO.LayoutParams layoutParams) {
        ContainerPOJO.LayoutParams lp = (ContainerPOJO.LayoutParams) layoutParams;
        FlexboxLayout.LayoutParams flexLayoutParams = new FlexboxLayout.LayoutParams(lp.getWidth(), lp.getHeight());
        flexLayoutParams.setOrder(lp.getOrder());
        return flexLayoutParams;
    }

    private @FlexDirection
    int flexDirection(String flexDirection) {
        if ("row_reverse".equals(flexDirection)) {
            return FlexDirection.ROW_REVERSE;
        } else if ("column".equals(flexDirection)) {
            return FlexDirection.COLUMN;
        } else if ("column_reverse".equalsIgnoreCase(flexDirection)) {
            return FlexDirection.COLUMN_REVERSE;
        } else {
            return FlexDirection.ROW;
        }
    }

    private @FlexWrap
    int flexWrap(String flexWrap) {
        if ("wrap".equalsIgnoreCase(flexWrap)) {
            return FlexWrap.WRAP;
        } else if ("wrap_reverse".equalsIgnoreCase(flexWrap)) {
            return FlexWrap.WRAP_REVERSE;
        } else {
            return FlexWrap.NOWRAP;
        }
    }
}
