package music.hayasi.android.com.mymusic.module.RadioButton;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;

public class RadioActivity extends BaseActivity {

    @Bind(R.id.radio_select_1)
    TextView textView1;
    @Bind(R.id.radio_select_2)
    TextView textView2;
    @Bind(R.id.radio_select_3)
    TextView textView3;

    @Bind(R.id.layout1)
    LinearLayout layout1;


    @Override
    protected int getContentViewResId() {
        return R.layout.radio_activity;
    }

    @Override
    public void initViews() {

    }

    @OnClick({R.id.radio_select_1, R.id.radio_select_2})
    public void onCLik(View v) {
        switch (v.getId()) {
            case R.id.radio_select_1:
                layout1.setSelected(false);
                textView1.setSelected(true);
                break;
            case R.id.radio_select_2:
                textView2.setSelected(true);
                break;
            case R.id.radio_select_3:
                textView3.setSelected(true);

                break;
            case R.id.layout1:

                break;
        }
    }

    @Override
    public void setListener() {


    }


    @Override
    public int getToolBarResId() {
        return 0;
    }

    @Override
    public void initToolBar(ToolBarManager navigationBarMgr) {

    }

}
