package freelifer.android.dl.view;

import org.json.JSONObject;

/**
 * @author kzhu on 2019/8/21.
 */
public class ConditionModel {
    private String operation;
    private ConditionModel left;
    private ConditionModel right;
    private String value;

    public static ConditionModel parse(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        ConditionModel conditionModel = new ConditionModel();
        conditionModel.operation = jsonObject.optString("operation");
        conditionModel.value = jsonObject.optString("value");

        JSONObject left = jsonObject.optJSONObject("left");
        JSONObject right = jsonObject.optJSONObject("right");
        if (left != null) {
            conditionModel.left = ConditionModel.parse(left);
        }
        if (right != null) {
            conditionModel.right = ConditionModel.parse(right);
        }
        return conditionModel;
    }

    public String getOperation() {
        return operation;
    }

    public ConditionModel getLeft() {
        return left;
    }

    public ConditionModel getRight() {
        return right;
    }

    public String getValue() {
        return value;
    }
}
