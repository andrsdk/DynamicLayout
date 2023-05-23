package freelifer.android.dl.framework.parser;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import freelifer.android.dl.framework.DView;
import freelifer.android.dl.framework.DViewContext;
import freelifer.android.dl.framework.processor.AttributeProcessor;
import freelifer.android.dl.framework.value.Layout;
import freelifer.android.dl.framework.value.Value;

/**
 * @author Ziv on 2022/7/6.
 */
public abstract class ViewTypeParser<V extends View> {

    public ViewTypeParser parent;

    private AttributeProcessor[] processors = new AttributeProcessor[0];

    private Map<String, AttributeSet.Attribute> attributes = new HashMap<>();

    private int offset = 0;

    private AttributeSet attributeSet;

    public AttributeSet prepare(ViewTypeParser parent, Map<String, AttributeProcessor<V>> extras) {
        this.parent = parent;
        this.processors = new AttributeProcessor[0];
        this.attributes = new HashMap<>();
        this.offset = null != parent ? parent.getAttributeSet().getOffset() : 0;

        addAttributeProcessors();

        if (extras != null) {
            addAttributeProcessors(extras);
        }

        this.attributeSet = new AttributeSet(attributes.size() > 0 ? attributes : null, null != parent ? parent.getAttributeSet() : null, processors.length);
        return attributeSet;
    }

    public boolean handleAttribute(V view, int attributeId, Value value) {
        int position = getPosition(attributeId);
        if (position < 0) {
            //noinspection unchecked
            return null != parent && parent.handleAttribute(view, attributeId, value);
        }
        AttributeProcessor attributeProcessor = processors[position];
        //noinspection unchecked
        attributeProcessor.process(view, value);
        return true;
    }

    public abstract String getType();

    public abstract String getParentType();

    protected abstract void addAttributeProcessors();

    public abstract DView createView(DViewContext context, Layout layout, ViewGroup parent, int dataIndex);

    protected void addAttributeProcessors(Map<String, AttributeProcessor<V>> processors) {
        for (Map.Entry<String, AttributeProcessor<V>> entry : processors.entrySet()) {
            addAttributeProcessor(entry.getKey(), entry.getValue());
        }
    }

    protected void addAttributeProcessor(String name, AttributeProcessor<V> processor) {
        addAttributeProcessor(processor);
        attributes.put(name, new AttributeSet.Attribute(getAttributeId(processors.length - 1), processor));
    }

    private void addAttributeProcessor(AttributeProcessor<V> handler) {
        processors = Arrays.copyOf(processors, processors.length + 1);
        processors[processors.length - 1] = handler;
    }

    public AttributeSet getAttributeSet() {
        return this.attributeSet;
    }

    private int getOffset() {
        return offset;
    }

    private int getPosition(int attributeId) {
        return attributeId + getOffset();
    }

    private int getAttributeId(int position) {
        return position - getOffset();
    }

    public int getAttributeId(String name) {
        AttributeSet.Attribute attribute = attributeSet.getAttribute(name);
        return null != attribute ? attribute.id : -1;
    }

    public static class AttributeSet {
        private final Map<String, Attribute> attributes;
        private final AttributeSet parent;
        private final int offset;

        AttributeSet(Map<String, Attribute> attributes, AttributeSet parent, int offset) {
            this.attributes = attributes;
            this.parent = parent;
            int parentOffset = null != parent ? parent.getOffset() : 0;
            this.offset = parentOffset - offset;
        }

        public Attribute getAttribute(String name) {
            Attribute attribute = null != attributes ? attributes.get(name) : null;
            if (null != attribute) {
                return attribute;
            } else if (null != parent) {
                return parent.getAttribute(name);
            } else {
                return null;
            }
        }

        int getOffset() {
            return offset;
        }

        public static class Attribute {
            public final int id;
            public final AttributeProcessor processor;

            Attribute(int id, AttributeProcessor processor) {
                this.processor = processor;
                this.id = id;
            }
        }
    }
}
