package freelifer.android.dl.sample;

import android.app.Application;

import com.facebook.soloader.SoLoader;

/**
 * @author kzhu on 2019/8/31.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SoLoader.init(this, false);
    }
}
