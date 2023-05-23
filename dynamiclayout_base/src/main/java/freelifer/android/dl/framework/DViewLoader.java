package freelifer.android.dl.framework;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import freelifer.android.dl.framework.parser.ViewTypeParser;

/**
 * @author Ziv on 2022/7/6.
 */
public class DViewLoader {

    private final Map<String, Type> types;

    private final Map<String, ViewTypeParser> parsers;

    DViewLoader(Map<String, Type> types) {
        this.types = types;
        this.parsers = map(types);
    }


    public DViewContext.Builder createContextBuilder(Context base) {
        return new DViewContext.Builder(base, parsers);
    }

    public boolean has(String type) {
        return types.containsKey(type);
    }

    public ViewTypeParser.AttributeSet.Attribute getAttributeId(String name, String type) {
        return types.get(type).getAttributeId(name);
    }

    private Map<String, ViewTypeParser> map(Map<String, Type> types) {
        Map<String, ViewTypeParser> parsers = new HashMap<>(types.size());
        for (Map.Entry<String, Type> entry : types.entrySet()) {
            parsers.put(entry.getKey(), entry.getValue().parser);
        }
        return parsers;
    }


    public static class Type {
        public final int id;
        public final String type;
        public final ViewTypeParser parser;

        private final ViewTypeParser.AttributeSet attributes;

        Type(int id, String type, ViewTypeParser parser, ViewTypeParser.AttributeSet attributes) {
            this.id = id;
            this.type = type;
            this.parser = parser;
            this.attributes = attributes;
        }

        public ViewTypeParser.AttributeSet.Attribute getAttributeId(String name) {
            return attributes.getAttribute(name);
        }
    }
}
