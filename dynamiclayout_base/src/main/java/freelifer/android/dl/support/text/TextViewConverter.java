package freelifer.android.dl.support.text;

import android.content.Context;

import freelifer.android.dl.view.AttributeSet;
import freelifer.android.dl.view.View;
import freelifer.android.dl.view.ViewConverter;

/**
 * @author kzhu on 2019/8/24.
 */
public class TextViewConverter implements ViewConverter {
    @Override
    public String getType() {
        return "text";
    }

    @Override
    public View getView(Context context, AttributeSet attrs) {
        return new TextView(context, attrs);
    }
}
