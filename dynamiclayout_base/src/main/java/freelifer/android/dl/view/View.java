package freelifer.android.dl.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * @author kzhu on 2019/8/24.
 */
public class View {
    private String type;
    private int width;
    private int height;

    private int paddingTop;
    private int paddingBottom;
    private int paddingLeft;
    private int paddingRight;

    private int background;

    private ConditionModel visibility;

    private android.view.View currentView;

    public View(Context context, AttributeSet attrs) {
        this.type = attrs.optString("type", "");
        this.height = attrs.getSize(context, "height", ViewGroup.LayoutParams.MATCH_PARENT);
        this.width = attrs.getSize(context, "width", ViewGroup.LayoutParams.MATCH_PARENT);

        this.paddingTop = attrs.optInt("paddingTop", 0);
        this.paddingBottom = attrs.optInt("paddingBottom", 0);
        this.paddingLeft = attrs.optInt("paddingLeft", 0);
        this.paddingRight = attrs.optInt("paddingRight", 0);

        this.background = attrs.getColor("background");
//        this.visibility = ConditionModel.parse(jsonObject.optJSONObject("visibility"));
    }

    public final void render(ViewGroup parent) {
        android.view.View view = getView(parent.getContext());
        renderView(view);
        addView(parent, view);
    }

    protected android.view.View getView(Context context) {
        return new android.view.View(context);
    }

    protected void renderView(android.view.View view) {
        Context context = view.getContext();
//        (int left, int top, int right, int bottom)
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(width, height);
        view.setLayoutParams(lp);
        view.setBackgroundColor(background);
    }

    protected void addView(ViewGroup parent, android.view.View view) {
        FrameLayout fl = new FrameLayout(parent.getContext());
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        parent.addView(fl, lp);
        fl.addView(view);
    }

}
