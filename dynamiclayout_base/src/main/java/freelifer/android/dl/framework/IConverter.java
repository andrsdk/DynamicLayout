package freelifer.android.dl.framework;

import android.view.ViewGroup;

import freelifer.android.dl.view.AttributeSet;

/**
 * @author kzhu on 2019/8/24.
 */
public interface IConverter {

    String getType();

    ViewPOJO getViewPOJO(AttributeSet attrs);

    //    View getView(ViewPOJO viewPOJO);
    void render(ViewGroup viewGroup, ViewPOJO viewPOJO);
}
