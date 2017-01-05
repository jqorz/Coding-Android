package net.coding.program.user;

import net.coding.program.MyApp;
import net.coding.program.R;
import net.coding.program.UserDetailEditActivity_;
import net.coding.program.common.Global;
import net.coding.program.model.UserObject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.json.JSONException;
import org.json.JSONObject;

@EActivity(R.layout.enterprise_activity_my_detail)
public class EnterpriseMyDetailActivity extends UserDetailCommonActivity {

    public final int RESULT_EDIT = 0;

    private final String HOST_USER_INFO = Global.HOST_API + "/user/key/";

    @AfterViews
    void initMyDetailActivity() {
        bindUI(MyApp.sUserObject);
        tv_follow_state.setText("编辑资料");
        rl_follow_state.setOnClickListener(v -> {
            UserDetailEditActivity_
                    .intent(this)
                    .startForResult(RESULT_EDIT);
        });

        final String HOST_USER_INFO = Global.HOST_API + "/user/key/";
        getNetwork(HOST_USER_INFO + MyApp.sUserObject.global_key, HOST_USER_INFO);
    }

    @Override
    public void parseJson(int code, JSONObject respanse, String tag, int pos, Object data) throws JSONException {
        if (tag.equals(HOST_USER_INFO)) {
            if (code == 0) {
                mUserObject = new UserObject(respanse.getJSONObject("data"));
                bindUI(mUserObject);
            } else {
                showButtomToast("获取用户信息错误");
            }
        }
        operActivenessResult(code, respanse, tag);
    }

    public int getActionBarSize() {
        return Global.dpToPx(48);
    }

}
