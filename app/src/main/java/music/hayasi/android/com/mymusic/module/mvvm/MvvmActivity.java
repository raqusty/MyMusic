package music.hayasi.android.com.mymusic.module.mvvm;

import android.databinding.DataBindingUtil;
import android.view.View;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.databinding.MvvmActivityBinding;
import music.hayasi.android.com.mymusic.module.mvvm.entity.Student;
import music.hayasi.android.com.mymusic.module.mvvm.entity.User;

public class MvvmActivity extends BaseActivity {

    private Student mStudent;
    private User mUser;
    private MvvmActivityBinding binding;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.mvvm_activity);
        mStudent = new Student("loade123r", "山东莱芜");
        binding.setStu(mStudent);
        mUser = new User("linzehao", "haifeng");
        binding.setUser(mUser);

        binding.addName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStudent.setName("lin");
                mUser.setFirstName("lin123");
                binding.setStu(mStudent);
            }
        });
        binding.setHandlers(new EventHandlers());

    }

    @Override
    public void setListener() {

    }

    @Override
    public int getToolBarResId() {
        return R.menu.main;
    }

    @Override
    public void initToolBar(ToolBarManager navigationBarMgr) {

    }

    public class EventHandlers {
        public void handleClick(View view) {
            mStudent.setName("linze");
            mUser.setFirstName("lin333");
            binding.setStu(mStudent);
        }
    }

}
