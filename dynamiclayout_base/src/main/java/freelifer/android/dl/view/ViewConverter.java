package freelifer.android.dl.view;

import android.content.Context;

/**
 * @author kzhu on 2019/8/24.
 */
public interface ViewConverter {

    String getType();

    View getView(Context context, AttributeSet attrs);
}
