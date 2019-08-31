package freelifer.android.dl.framework;

import freelifer.android.dl.view.AttributeSet;

/**
 * @author kzhu on 2019/8/31.
 */
public class ViewPOJO {
    private String type;

    private int paddingTop;
    private int paddingBottom;
    private int paddingLeft;
    private int paddingRight;

    private int background;
    private ViewGroupPOJO.LayoutParams layoutParams;

    public ViewPOJO(AttributeSet attrs) {
        this.type = attrs.optString("type", "");

        this.paddingTop = attrs.optInt("paddingTop", 0);
        this.paddingBottom = attrs.optInt("paddingBottom", 0);
        this.paddingLeft = attrs.optInt("paddingLeft", 0);
        this.paddingRight = attrs.optInt("paddingRight", 0);

        this.background = attrs.getColor("background");
//        this.visibility = ConditionModel.parse(jsonObject.optJSONObject("visibility"));
    }

    public String getType() {
        return type;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public int getPaddingBottom() {
        return paddingBottom;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public int getBackground() {
        return background;
    }

    public ViewGroupPOJO.LayoutParams getLayoutParams() {
        return layoutParams;
    }

    public void setLayoutParams(ViewGroupPOJO.LayoutParams params) {
        this.layoutParams = params;
    }
}
