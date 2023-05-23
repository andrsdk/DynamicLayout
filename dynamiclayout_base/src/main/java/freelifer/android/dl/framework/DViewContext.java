package freelifer.android.dl.framework;

import android.content.Context;
import android.content.ContextWrapper;

import java.util.Map;

import freelifer.android.dl.framework.parser.ViewTypeParser;

/**
 * @author Ziv on 2022/7/6.
 */
public class DViewContext extends ContextWrapper {

    private final DViewResources resources;
    private DViewLayoutInflater inflater;

    public DViewContext(Context base, DViewResources resources) {
        super(base);
        this.resources = resources;
    }

    public ViewTypeParser getParser(String type) {
        return resources.getParsers().get(type);
    }

    public DViewLayoutInflater getInflater() {
        if (null == this.inflater) {
            this.inflater = new DViewLayoutInflaterImpl(this);
        }
        return this.inflater;
    }

    public static class Builder {
        private final Context base;
        private final Map<String, ViewTypeParser> parsers;

        Builder(Context context, Map<String, ViewTypeParser> parsers) {
            this.base = context;
            this.parsers = parsers;
        }

        public DViewContext build() {
            DViewResources resources = new DViewResources(parsers);
            return new DViewContext(base, resources);
        }
    }
}
