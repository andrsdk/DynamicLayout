package freelifer.android.dl.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import freelifer.android.dl.support.text.TextViewConverter;
import freelifer.android.dl.view.DefaultViewConverter;
import freelifer.android.dl.view.LayoutInflater;
import freelifer.android.dl.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewGroup viewGroup = findViewById(R.id.parentLinear);

//        DynamicLayout dl = ViewModelParser.parse(getAssetsFile("1001.json"));
//        dl.render(viewGroup);
        LayoutInflater.addViewConverter(new DefaultViewConverter());
        LayoutInflater.addViewConverter(new TextViewConverter());
        View view = LayoutInflater.from().inflate(this, getAssetsFile("1001.json"));
        view.render(viewGroup);
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
