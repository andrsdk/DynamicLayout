package freelifer.android.dl.framework;

import android.view.View;

/**
 * @author Ziv on 2022/7/6.
 */
public interface DView {

    Manager getViewManager();

    void setViewManager(Manager manager);

    View getAsView();

    interface Manager {

    }
}
