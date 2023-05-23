package freelifer.android.dl.framework;

import android.util.Log;
import android.view.InflateException;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.Iterator;

import freelifer.android.dl.framework.parser.ViewTypeParser;
import freelifer.android.dl.framework.value.Layout;
import freelifer.android.dl.framework.value.Value;

/**
 * @author Ziv on 2022/7/6.
 */
public class DViewLayoutInflaterImpl implements DViewLayoutInflater {

    protected final DViewContext context;

    public DViewLayoutInflaterImpl(DViewContext context) {
        this.context = context;
    }

    @Override
    public DView inflate(Layout layout, ViewGroup parent, int dataIndex) {
        final ViewTypeParser parser = getParser(layout.type);
        if (parser == null) {
            /*
             * If parser is not registered ask the application land for the view
             */
//            return onUnknownViewEncountered(layout.type, layout, data, dataIndex);
            throw new InflateException("Unknown match type{" + layout.type + "}");
        }
        final DView view = createView(parser, layout, parent, dataIndex);

        if (!layout.isAttributeEmpty()) {
            Iterator<Layout.Attribute> iterator = layout.attributes.iterator();
            Layout.Attribute attribute;
            while (iterator.hasNext()) {
                attribute = iterator.next();
                handleAttribute(parser, view, attribute.id, attribute.value);
            }
        }

        return view;
    }

    @Override
    public ViewTypeParser getParser(String type) {
        return context.getParser(type);
    }

    protected DView createView(ViewTypeParser parser, Layout layout, ViewGroup parent, int dataIndex) {
        return parser.createView(context, layout, parent, dataIndex);
    }


    protected boolean handleAttribute(ViewTypeParser parser, DView view, int attribute, Value value) {
//        if (ProteusConstants.isLoggingEnabled()) {
//            Log.d(TAG, "Handle '" + attribute + "' : " + value);
//        }
        //noinspection unchecked
        return parser.handleAttribute(view.getAsView(), attribute, value);
    }

}
