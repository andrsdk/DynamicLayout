package freelifer.android.dl.common;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

/**
 * @author kzhu on 2019/8/22.
 */
public class Kits {

    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(@Nullable CharSequence str) {
        return !isEmpty(str);
    }

    public static int getColor(String color) {
        if (isNotEmpty(color)) {
            try {
                return Color.parseColor(color);
            } catch (Exception e) {
                // ignore
                if (Mode.isDebug()) {
                    e.printStackTrace();
                }
            }
        }
        return 0xFF000000;
    }

    // match、wrap、20dp、20px(默认)
    public static int getSize(Context context, String size) {
        if (isEmpty(size)) {
            return 0;
        }
        if ("wrap".equals(size)) {
            return ViewGroup.LayoutParams.WRAP_CONTENT;
        } else if ("match".equals(size)) {
            return ViewGroup.LayoutParams.MATCH_PARENT;
        } else {
            try {
                return applyDimension(context, size);
            } catch (Exception e) {
                return 0;
            }
        }
    }

    private static int applyDimension(Context context, String cs) {
        int index = -1;
        int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(cs.charAt(i))) {
                index = i;
            }
        }
        if (index >= 1) {
            String number = cs.substring(0, index + 1);
            if (isEmpty(number)) {
                return 0;
            }
            int unit = "dp".equals(cs.substring(index + 1)) ? TypedValue.COMPLEX_UNIT_DIP : TypedValue.COMPLEX_UNIT_PX;
            return (int) TypedValue.applyDimension(unit, Integer.valueOf(number), context.getResources().getDisplayMetrics());
        }
        return 0;
    }
}
