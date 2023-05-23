package freelifer.android.dl.framework.value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ziv on 2022/7/6.
 */
public class Layout extends Value {

    public final String type;

    public final List<Attribute> attributes;

    public final Map<String, Value> data;

    public Layout(String type, List<Attribute> attributes, Map<String, Value> data) {
        this.type = type;
        this.attributes = attributes;
        this.data = data;
    }

    public boolean isAttributeEmpty() {
        return attributes == null || attributes.isEmpty();
    }

    @Override
    public Value copy() {
        List<Attribute> attributes = null;
        if (this.attributes != null) {
            attributes = new ArrayList<>(this.attributes.size());
            for (Attribute attribute : this.attributes) {
                attributes.add(attribute.copy());
            }
        }

        return new Layout(type, attributes, data);
    }

    public static class Attribute {

        public final int id;
        public final Value value;

        public Attribute(int id, Value value) {
            this.id = id;
            this.value = value;
        }

        protected Attribute copy() {
            return new Attribute(id, value.copy());
        }
    }
}
