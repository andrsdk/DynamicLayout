package freelifer.android.dl.view;

import android.content.Context;

/**
 * @author kzhu on 2019/8/24.
 */
public class DefaultViewConverter implements ViewConverter {

    @Override
    public String getType() {
        return "view";
    }

    @Override
    public View getView(Context context, AttributeSet attrs) {
        return new View(context, attrs);
    }
}