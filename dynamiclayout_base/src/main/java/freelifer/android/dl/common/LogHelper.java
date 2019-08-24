package freelifer.android.dl.common;

import android.util.Log;

/**
 * @author kzhu on 2019/7/31.
 */
public class LogHelper {

    private static final String tag = "DynamicLayout-1.0.0";

    public static void i(String msg) {
        if (Mode.isDebug()) {
            Log.i(tag, msg);
        }
    }

    public static void d(String msg) {
        if (Mode.isDebug()) {
            Log.d(tag, msg);
        }
    }

    public static void dF(String format, Object... args) {
        if (Mode.isDebug()) {
            Log.d(tag, String.format(format, args));
        }
    }

    public static void w(String msg) {
        if (Mode.isDebug()) {
            Log.w(tag, msg);
        }
    }

    public static void e(String msg) {
        if (Mode.isDebug()) {
            Log.e(tag, msg);
        }
    }
}
