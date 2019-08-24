package freelifer.android.dl.support.text;

import android.content.Context;
import android.util.TypedValue;

import freelifer.android.dl.view.AttributeSet;
import freelifer.android.dl.view.View;

/**
 * @author kzhu on 2019/8/24.
 */
public class TextView extends View {
    private String text;
    private int fontColor;
    private int fontSize;

    public TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.text = attrs.optString("text", "");
        this.fontColor = attrs.getColor("fontColor");
        this.fontSize = attrs.getSize(context, "fontSize", 0);
    }

    @Override
    public android.widget.TextView getView(Context context) {
        return new android.widget.TextView(context);
    }

    @Override
    protected void renderView(android.view.View view) {
        super.renderView(view);
        android.widget.TextView textView = (android.widget.TextView) view;
        textView.setText(this.text);
        textView.setTextColor(fontColor);

        if (fontSize != 0) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);
        }
    }
}
