package music.hayasi.android.com.mymusic.module.Path;

import android.graphics.PointF;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.module.Path.sp.pathSp;
import music.hayasi.android.com.mymusic.module.Path.widget.Bezier2;

public class Path2Activity extends BaseActivity {

    @Bind(R.id.my_bezier)
    Bezier2 mBezier;

    @Bind(R.id.id_x_1)
    EditText mX1;
    @Bind(R.id.id_x_2)
    EditText mX2;
    @Bind(R.id.id_x_3)
    EditText mX3;
    @Bind(R.id.id_x_4)
    EditText mX4;

    @Bind(R.id.id_y_1)
    EditText mY1;
    @Bind(R.id.id_y_2)
    EditText mY2;
    @Bind(R.id.id_y_3)
    EditText mY3;
    @Bind(R.id.id_y_4)
    EditText mY4;

    @Bind(R.id.group_spinner)
    Spinner group_spinner;
    @Bind(R.id.point_spinner)
    Spinner point_spinner;
    @Bind(R.id.layout_spinner)
    Spinner layout_spinner;

    @Bind(R.id.radio_group)
    RadioGroup mRadioGroup;

    private pathSp mSp;

    private int curGroup = 0;
    private int curpoint = 0;
    private int curlayout = 0;
    private List<String> groupList;
    private ArrayAdapter groupAdapter;
    private List<String> pointList;
    private ArrayAdapter pointAdapter;
    private List<String> layoutList;
    private ArrayAdapter layoutAdapter;


    @Override
    protected int getContentViewResId() {
        return R.layout.path_2_activity;
    }

    @Override
    public void initViews() {
        mSp = new pathSp(mContext);
        //数据
        groupList = new ArrayList<String>();
        groupList.add("1");
        groupList.add("2");

        pointList = new ArrayList<String>();
        pointList.add("1");
        pointList.add("2");
        pointList.add("3");
        pointList.add("4");

        layoutList = new ArrayList<String>();
        layoutList.add("1");
        layoutList.add("2");
        layoutList.add("3");

        groupAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, groupList);
        groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        group_spinner.setAdapter(groupAdapter);

        pointAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pointList);
        pointAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        point_spinner.setAdapter(pointAdapter);


        layoutAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, layoutList);
        layoutAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        layout_spinner.setAdapter(layoutAdapter);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroupListener());
    }

    @Override
    public void setListener() {
        group_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("linzehao", "123123");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        point_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("linzehao", "444444444444");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radio_point1:
                    Toast.makeText(mContext, "radio_point1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.radio_point2:
                    Toast.makeText(mContext, "radio_point2", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.radio_point3:
                    Toast.makeText(mContext, "radio_point3", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.radio_point4:
                    Toast.makeText(mContext, "radio_point4", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    class MenuItemClickListene implements PopupMenu.OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.add_group:
                    Toast.makeText(mContext, "add group", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.add_layout:
                    Toast.makeText(mContext, "group", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.add_point:
                    Toast.makeText(mContext, "add point", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            return false;
        }
    }

    @OnClick({R.id.huizhi, R.id.add, R.id.sava, R.id.run})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.huizhi:
                mBezier.setDo(true);
                PointF point1 = new PointF(Float.parseFloat(mX1.getText().toString()), Float.parseFloat(mY1.getText().toString()));
                PointF point2 = new PointF(Float.parseFloat(mX2.getText().toString()), Float.parseFloat(mY2.getText().toString()));
                PointF point3 = new PointF(Float.parseFloat(mX3.getText().toString()), Float.parseFloat(mY3.getText().toString()));
                PointF point4 = new PointF(Float.parseFloat(mX4.getText().toString()), Float.parseFloat(mY4.getText().toString()));
                mBezier.setPoint(point1, point2, point3, point4);
                break;

            case R.id.add:
                PopupMenu popup = new PopupMenu(this, v);//第二个参数是绑定的那个view
                //获取菜单填充器
                MenuInflater inflater = popup.getMenuInflater();
                //填充菜单
                inflater.inflate(R.menu.path, popup.getMenu());
                //绑定菜单项的点击事件
                popup.setOnMenuItemClickListener(new MenuItemClickListene());
                //显示(这一行代码不要忘记了)
                popup.show();
                break;

            case R.id.sava:
                mSp.putValue(mSp.getKey(curGroup + "", curpoint + "", "x1"), (mX1.getText().toString()));
                mSp.putValue(mSp.getKey(curGroup + "", curpoint + "", "x2"), (mX2.getText().toString()));
                mSp.putValue(mSp.getKey(curGroup + "", curpoint + "", "x3"), (mX3.getText().toString()));
                mSp.putValue(mSp.getKey(curGroup + "", curpoint + "", "x4"), (mX4.getText().toString()));
                mSp.putValue(mSp.getKey(curGroup + "", curpoint + "", "y1"), (mY1.getText().toString()));
                mSp.putValue(mSp.getKey(curGroup + "", curpoint + "", "y2"), (mY2.getText().toString()));
                mSp.putValue(mSp.getKey(curGroup + "", curpoint + "", "y3"), (mY3.getText().toString()));
                mSp.putValue(mSp.getKey(curGroup + "", curpoint + "", "y4"), (mY4.getText().toString()));
                break;

            case R.id.run:

                mX1.setText(mSp.getValue(mSp.getKey(curGroup + "", curpoint + "", "x1"), "0"));
                mX2.setText(mSp.getValue(mSp.getKey(curGroup + "", curpoint + "", "x2"), "0"));
                mX3.setText(mSp.getValue(mSp.getKey(curGroup + "", curpoint + "", "x3"), "0"));
                mX4.setText(mSp.getValue(mSp.getKey(curGroup + "", curpoint + "", "x4"), "0"));
                mY1.setText(mSp.getValue(mSp.getKey(curGroup + "", curpoint + "", "y1"), "0"));
                mY2.setText(mSp.getValue(mSp.getKey(curGroup + "", curpoint + "", "y2"), "0"));
                mY3.setText(mSp.getValue(mSp.getKey(curGroup + "", curpoint + "", "y3"), "0"));
                mY4.setText(mSp.getValue(mSp.getKey(curGroup + "", curpoint + "", "y4"), "0"));
                break;
        }

    }

    @Override
    public int getToolBarResId() {
        return 0;
    }

    @Override
    public void initToolBar(ToolBarManager navigationBarMgr) {

    }

}
