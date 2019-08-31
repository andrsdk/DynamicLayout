package freelifer.android.dl.sample;

import android.os.Bundle;

import com.facebook.litho.ComponentContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import androidx.appcompat.app.AppCompatActivity;
import freelifer.android.dl.framework.LayoutDataInflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ComponentContext context = new ComponentContext(this);

        // test LithoView
//        final Component component = Text.create(context)
//                .text("Hello World")
//                .textSizeDip(50)
//                .build();
//
//        setContentView(LithoView.create(context, component));
        // test LithoView end

        setContentView(R.layout.activity_main);

        LayoutDataInflater.from(this).inflatView(getAssetsFile("1001.json"));


//        ViewGroup viewGroup = findViewById(R.id.parentLinear);

//        DynamicLayout dl = ViewModelParser.parse(getAssetsFile("1001.json"));
//        dl.render(viewGroup);

//        LayoutInflater.addViewConverter(new DefaultViewConverter());
//        LayoutInflater.addViewConverter(new TextViewConverter());
//        View view = LayoutInflater.from().inflate(this, getAssetsFile("1001.json"));
//        view.render(viewGroup);
//        FlexboxLayout flexboxLayout = new FlexboxLayout(this);
//        flexboxLayout.setFlexDirection(FlexDirection.ROW);


    }


    private String getAssetsFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream is = getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine(); // 读取第一行
            while (line != null) { // 如果 line 为空说明读完了
                stringBuilder.append(line); // 将读到的内容添加到 buffer 中
                stringBuilder.append("\n"); // 添加换行符
                line = reader.readLine(); // 读取下一行
            }
            reader.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
