package music.hayasi.android.com.mymusic.module.Path;

import android.graphics.PointF;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
import music.hayasi.android.com.mymusic.module.Path.adapter.SpinnerAdapter;
import music.hayasi.android.com.mymusic.module.Path.dataBase.DatabaseUtils;
import music.hayasi.android.com.mymusic.module.Path.entity.PathEntity;
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

    DatabaseUtils dataBase;

    private PathEntity curGroup = new PathEntity();
    private PathEntity curLayout = new PathEntity();
    private PathEntity curPoint = new PathEntity();

    private List<PathEntity> groupList;
    private SpinnerAdapter groupAdapter;
    private List<PathEntity> pointList;
    private SpinnerAdapter pointAdapter;
    private List<PathEntity> layoutList;
    private SpinnerAdapter layoutAdapter;

    @Override
    protected int getContentViewResId() {
        return R.layout.path_2_activity;
    }

    @Override
    public void initViews() {
        dataBase = new DatabaseUtils(mContext);
        //数据
        groupList = new ArrayList<PathEntity>();
        pointList = new ArrayList<PathEntity>();
        layoutList = new ArrayList<PathEntity>();
        setAdapterData(1);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroupListener());
    }

    @Override
    public void setListener() {
        group_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (groupList.get(i) != curGroup) {
                    curGroup = groupList.get(i);
                    setAdapterData(2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        layout_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (layoutList.get(i) != curLayout) {
                    curLayout = layoutList.get(i);
                    setAdapterData(3);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        point_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (curPoint != pointList.get(i)) {
                    curPoint = pointList.get(i);
                    refreshPoint();
                }
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
                    setGroup();
                    Toast.makeText(mContext, "add group", Toast.LENGTH_SHORT).show();
                    setAdapterData(1);
                    break;
                case R.id.add_layout:
                    setLayout();
                    Toast.makeText(mContext, "group", Toast.LENGTH_SHORT).show();
                    setAdapterData(2);
                    break;
                case R.id.add_point:
                    setPoint();
                    dataBase.insertData(curPoint);
                    Toast.makeText(mContext, "add point1", Toast.LENGTH_SHORT).show();
                    setAdapterData(3);
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
                setPoint();
                PointF point1 = new PointF(curPoint.getPoint1_x(), curPoint.getPoint1_y());
                PointF point2 = new PointF(curPoint.getPoint2_x(), curPoint.getPoint2_y());
                PointF point3 = new PointF(curPoint.getPoint3_x(), curPoint.getPoint3_y());
                PointF point4 = new PointF(curPoint.getPoint4_x(), curPoint.getPoint4_y());
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
                setPoint();
                dataBase.updataData(curPoint);
                break;

            case R.id.run:
                refreshPoint();
                break;
        }

    }

    private void setPoint() {
        curPoint.setParentId(curLayout.getId());

        curPoint.setPoint1_x(Float.parseFloat(mX1.getText().toString()));
        curPoint.setPoint1_y(Float.parseFloat(mY1.getText().toString()));
        curPoint.setPoint2_x(Float.parseFloat(mX2.getText().toString()));
        curPoint.setPoint2_y(Float.parseFloat(mY2.getText().toString()));
        curPoint.setPoint3_x(Float.parseFloat(mX3.getText().toString()));
        curPoint.setPoint3_y(Float.parseFloat(mY3.getText().toString()));
        curPoint.setPoint4_x(Float.parseFloat(mX4.getText().toString()));
        curPoint.setPoint4_y(Float.parseFloat(mY4.getText().toString()));
    }

    private void refreshPoint() {
        mBezier.setDo(true);
        mX1.setText(curPoint.getPoint1_x() + "");
        mY1.setText(curPoint.getPoint1_y() + "");
        mX2.setText(curPoint.getPoint2_x() + "");
        mY2.setText(curPoint.getPoint2_y() + "");
        mX3.setText(curPoint.getPoint3_x() + "");
        mY3.setText(curPoint.getPoint3_y() + "");
        mX4.setText(curPoint.getPoint4_x() + "");
        mY4.setText(curPoint.getPoint4_y() + "");

        PointF point1 = new PointF(curPoint.getPoint1_x(), curPoint.getPoint1_y());
        PointF point2 = new PointF(curPoint.getPoint2_x(), curPoint.getPoint2_y());
        PointF point3 = new PointF(curPoint.getPoint3_x(), curPoint.getPoint3_y());
        PointF point4 = new PointF(curPoint.getPoint4_x(), curPoint.getPoint4_y());
        mBezier.setPoint(point1, point2, point3, point4);
    }

    private void setLayout() {
        dataBase.insertDataParentID(curLayout);
    }

    private void setGroup() {
        dataBase.insertDataParentID(curGroup);
        curLayout.setParentId(curGroup.getId());
        dataBase.insertDataParentID(curLayout);
    }

    //获取到group 会刷新 layout point的数据，
    //获取layout 会刷新 point的数据
    // 1： group   2：layout   3：point
    private void setAdapterData(int type) {
        if (type == 1) {
            groupList.clear();
            groupList.addAll(dataBase.getData(0))  ;
            if (groupAdapter == null) {
                groupAdapter = new SpinnerAdapter(mContext, groupList);
                group_spinner.setAdapter(groupAdapter);
            } else {
                groupAdapter.notifyDataSetChanged();
            }
            if (groupList.size() != 0) {
                //默认取最后一个
                group_spinner.setSelection(groupList.size() - 1);
                curGroup = groupList.get(groupList.size() - 1);

                setAdapterData(2);
            }

        } else if (type == 2) {
            layoutList.clear();
            layoutList.addAll(dataBase.getData(curGroup.getId()))  ;
            if (layoutAdapter == null) {
                layoutAdapter = new SpinnerAdapter(mContext, layoutList);
                layout_spinner.setAdapter(layoutAdapter);
            } else {
                layoutAdapter.notifyDataSetChanged();
            }
            if (layoutList.size() != 0) {
                //默认取最后一个
                layout_spinner.setSelection(layoutList.size() - 1);
                curLayout = layoutList.get(layoutList.size() - 1);

                setAdapterData(3);
            }

        } else if (type == 3) {
            pointList.clear();
            pointList.addAll(dataBase.getData(curLayout.getId()))  ;
            if (pointAdapter == null) {
                pointAdapter = new SpinnerAdapter(mContext, pointList);
                point_spinner.setAdapter(pointAdapter);
            } else {
                pointAdapter.notifyDataSetChanged();
            }
            if (pointList.size() != 0) {
                //默认取最后一个
                point_spinner.setSelection(pointList.size() - 1);
                curPoint = pointList.get(pointList.size() - 1);
                refreshPoint();
            }
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
