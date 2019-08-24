package freelifer.android.dl.view;

import android.content.Context;

/**
 * @author kzhu on 2019/8/24.
 */
public class ViewGroup extends View {
    private static final int ARRAY_CAPACITY_INCREMENT = 12;

    private View[] mChildren;
    private int mChildrenCount;

    public ViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LayoutParams generateLayoutParams() {
        return new LayoutParams();
    }

    public void addView(View view, LayoutParams params) {
        addInArray(view, mChildrenCount);
    }


    private void addInArray(View child, int index) {
        View[] children = mChildren;
        final int count = mChildrenCount;
        final int size = children.length;
        if (index == count) {
            if (size == count) {
                mChildren = new View[size + ARRAY_CAPACITY_INCREMENT];
                System.arraycopy(children, 0, mChildren, 0, size);
                children = mChildren;
            }
            children[mChildrenCount++] = child;
        } else if (index < count) {
            if (size == count) {
                mChildren = new View[size + ARRAY_CAPACITY_INCREMENT];
                System.arraycopy(children, 0, mChildren, 0, index);
                System.arraycopy(children, index, mChildren, index + 1, count - index);
                children = mChildren;
            } else {
                System.arraycopy(children, index, children, index + 1, count - index);
            }
            children[index] = child;
            mChildrenCount++;
        } else {
            throw new IndexOutOfBoundsException("index=" + index + " count=" + count);
        }
    }


    public static class LayoutParams {
    }
}
