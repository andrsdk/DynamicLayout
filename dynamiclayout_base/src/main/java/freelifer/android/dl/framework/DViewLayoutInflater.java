package freelifer.android.dl.framework;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import freelifer.android.dl.framework.parser.ViewTypeParser;
import freelifer.android.dl.framework.value.Layout;

/**
 * @author Ziv on 2022/7/6.
 */
public interface DViewLayoutInflater {

    DView inflate(Layout layout, ViewGroup parent, int dataIndex);

    ViewTypeParser getParser(@NonNull String type);
}
