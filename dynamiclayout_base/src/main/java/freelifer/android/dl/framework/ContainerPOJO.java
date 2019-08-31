package freelifer.android.dl.framework;

import freelifer.android.dl.view.AttributeSet;

/**
 * @author kzhu on 2019/8/31.
 */
public class ContainerPOJO extends ViewGroupPOJO {
    private String flexDirection;
    private String flexWrap;
    //    private String flexFlow;
    private String justifyContent;
    private String alignItems;
    private String alignContent;

    public ContainerPOJO(AttributeSet attrs) {
        super(attrs);
        this.flexDirection = attrs.optString("flexDirection", "");
        this.flexWrap = attrs.optString("flexWrap", "");
        this.justifyContent = attrs.optString("justifyContent", "");
        this.alignItems = attrs.optString("alignItems", "");
        this.alignContent = attrs.optString("alignContent", "");
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(attrs);
    }

    public String getFlexDirection() {
        return flexDirection;
    }

    public String getFlexWrap() {
        return flexWrap;
    }

    public String getJustifyContent() {
        return justifyContent;
    }

    public String getAlignItems() {
        return alignItems;
    }

    public String getAlignContent() {
        return alignContent;
    }

    public static class LayoutParams extends MarginLayoutParams {

        private int order;
        private String flexGrow;
        private String flexShrink;
        private String alignSelf;
        private String flexBasisPercent;
        private String wrapBefore;

        public LayoutParams(AttributeSet attrs) {
            super(attrs);

            order = attrs.optInt("lp_order", 1);
            flexGrow = attrs.optString("lp_flexGrow", "");
            flexShrink = attrs.optString("lp_flexShrink", "");
            alignSelf = attrs.optString("lp_alignSelf", "");
            flexBasisPercent = attrs.optString("lp_flexBasisPercent", "");
            wrapBefore = attrs.optString("lp_wrapBefore", "");
        }

        public int getOrder() {
            return order;
        }

        public String getFlexGrow() {
            return flexGrow;
        }

        public String getFlexShrink() {
            return flexShrink;
        }

        public String getAlignSelf() {
            return alignSelf;
        }

        public String getFlexBasisPercent() {
            return flexBasisPercent;
        }

        public String getWrapBefore() {
            return wrapBefore;
        }
    }
}
