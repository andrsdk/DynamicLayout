package freelifer.android.dl.framework;

import android.view.ViewGroup;

import freelifer.android.dl.view.AttributeSet;

/**
 * @author kzhu on 2019/8/31.
 */
public class ViewGroupPOJO extends ViewPOJO {

    private static final int ARRAY_CAPACITY_INCREMENT = 12;

    private ViewPOJO[] children;
    private int childrenCount;

    public ViewGroupPOJO(AttributeSet attrs) {
        super(attrs);

    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(attrs);
    }

    public void addViewPOJO(ViewPOJO child, LayoutParams params) {
        addInArray(child, childrenCount);
        child.setLayoutParams(params);
    }

    public ViewPOJO[] getChildren() {
        return children;
    }

    public int getChildrenCount() {
        return childrenCount;
    }

    private void addInArray(ViewPOJO child, int index) {
        ViewPOJO[] children = this.children;
        final int count = childrenCount;
        final int size = children.length;
        if (index == count) {
            if (size == count) {
                this.children = new ViewPOJO[size + ARRAY_CAPACITY_INCREMENT];
                System.arraycopy(children, 0, this.children, 0, size);
                children = this.children;
            }
            children[childrenCount++] = child;
        } else if (index < count) {
            if (size == count) {
                this.children = new ViewPOJO[size + ARRAY_CAPACITY_INCREMENT];
                System.arraycopy(children, 0, this.children, 0, index);
                System.arraycopy(children, index, this.children, index + 1, count - index);
                children = this.children;
            } else {
                System.arraycopy(children, index, children, index + 1, count - index);
            }
            children[index] = child;
            childrenCount++;
        } else {
            throw new IndexOutOfBoundsException("index=" + index + " count=" + count);
        }
    }


    public static class LayoutParams {
        private int width;
        private int height;

        public LayoutParams(AttributeSet attrs) {
            this.height = attrs.getSize("lp_height", ViewGroup.LayoutParams.WRAP_CONTENT);
            this.width = attrs.getSize("lp_width", ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        public LayoutParams(LayoutParams source) {
            this.width = source.width;
            this.height = source.height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

//        public ViewGroup.LayoutParams generateLayoutParams(ViewGroup child) {
//            return null;
//        }
    }

    public static class MarginLayoutParams extends LayoutParams {

        private int marginTop;
        private int marginBottom;
        private int marginLeft;
        private int marginRight;

        public MarginLayoutParams(AttributeSet attrs) {
            super(attrs);

            this.marginTop = attrs.getSize("lp_marginTop", 0);
            this.marginBottom = attrs.getSize("lp_marginBottom", 0);
            this.marginLeft = attrs.getSize("lp_marginLeft", 0);
            this.marginRight = attrs.getSize("lp_marginRight", 0);
        }
    }
}
