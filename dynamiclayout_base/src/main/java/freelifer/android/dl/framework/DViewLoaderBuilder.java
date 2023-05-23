package freelifer.android.dl.framework;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import freelifer.android.dl.framework.parser.ViewParser;
import freelifer.android.dl.framework.parser.ViewTypeParser;
import freelifer.android.dl.framework.processor.AttributeProcessor;

/**
 * @author Ziv on 2022/7/6.
 */
public class DViewLoaderBuilder {

    public static final DViewLoaderBuilder.Module DEFAULT_MODULE = new DViewLoaderBuilder.Module() {

        @Override
        public void registerWith(DViewLoaderBuilder builder) {
            builder.register(new ViewParser());
        }
    };

    private static final int ID = -1;
    private Map<String, ViewTypeParser> parsers = new LinkedHashMap<>();
    private Map<String, Map<String, AttributeProcessor>> processors = new LinkedHashMap<>();

    public DViewLoaderBuilder() {
        DEFAULT_MODULE.registerWith(this);
    }

    public DViewLoaderBuilder register(ViewTypeParser parser) {
        String parentType = parser.getParentType();
        if (parentType != null && !parsers.containsKey(parentType)) {
            throw new IllegalStateException(parentType + " is not a registered type parser");
        }
        parsers.put(parser.getType(), parser);
        return this;
    }

    public DViewLoader build() {
        Map<String, DViewLoader.Type> types = new HashMap<>();
        for (Map.Entry<String, ViewTypeParser> entry : parsers.entrySet()) {
            types.put(entry.getKey(), prepare(entry.getValue()));
        }
        return new DViewLoader(types);
    }

    protected DViewLoader.Type prepare(ViewTypeParser parser) {
        String name = parser.getType();
        ViewTypeParser parent = parsers.get(parser.getParentType());
        Map<String, AttributeProcessor> extras = this.processors.get(name);

        //noinspection unchecked
        return new DViewLoader.Type(ID, name, parser, parser.prepare(parent, extras));
    }


    public interface Module {

        void registerWith(DViewLoaderBuilder builder);

    }
}
