package music.hayasi.android.com.mymusic.module.Guide;

import android.databinding.DataBindingUtil;
import android.view.View;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.databinding.AnimActivityBinding;
import music.hayasi.android.com.mymusic.module.Guide.Guide.Component;
import music.hayasi.android.com.mymusic.module.Guide.Guide.Guide;
import music.hayasi.android.com.mymusic.module.Guide.Guide.GuideBuilder;

public class GuideActivity extends BaseActivity {

    private AnimActivityBinding binding;


    @Override
    protected int getContentViewResId() {
        return 0;
    }

    @Override
    public void initViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.anim_activity);
        binding.button1.setText("新手引导1");
        binding.button2.setText("新手引导2");
        binding.button3.setText("新手引导3");
        binding.button4.setText("新手引导4");
        binding.button5.setText("新手引导5");
        binding.button6.setText("新手引导6");

    }

    private void showGuideView1() {
        new GuideBuilder.Builder(mContext, binding.button1)
                .addComponent(new Simple1Component())
                .GuideListten(new Guide.GuideListten() {
                    @Override
                    public void showGuide() {

                    }

                    @Override
                    public void dissGuide() {
                        showGuideView2();
                    }
                })
                .Style(Component.CIRCLE)
                .Corner(20)
                .build();
    }

    private void showGuideView2() {
        new GuideBuilder.Builder(mContext, binding.button2)
                .addComponent(new Simple2Component())
                .GuideListten(new Guide.GuideListten() {
                    @Override
                    public void showGuide() {

                    }

                    @Override
                    public void dissGuide() {
                        showGuideView3();
                    }
                })
                .Corner(20)
                .build();
    }

    private void showGuideView3() {
        new GuideBuilder.Builder(mContext, binding.button3)
                .addComponent(new Simple3Component())
                .GuideListten(new Guide.GuideListten() {
                    @Override
                    public void showGuide() {

                    }

                    @Override
                    public void dissGuide() {
                        showGuideView4();
                    }
                })
                .Corner(20)
                .build();
    }

    private void showGuideView4() {

        new GuideBuilder.Builder(mContext, binding.button4)
                .addComponent(new Simple4Component())
                .GuideListten(new Guide.GuideListten() {
                    @Override
                    public void showGuide() {

                    }

                    @Override
                    public void dissGuide() {
                        showGuideView5();
                    }
                })
                .Corner(20)
                .build();
    }

    private void showGuideView5() {
        new GuideBuilder.Builder(mContext, binding.button5)
                .addComponent(new Simple5Component())
                .addComponent(new Simple1Component())
                .GuideListten(new Guide.GuideListten() {
                    @Override
                    public void showGuide() {

                    }

                    @Override
                    public void dissGuide() {
                        showGuideView6();
                    }
                })
                .Corner(20)
                .build();

    }

    private void showGuideView6() {
        new GuideBuilder.Builder(mContext, binding.button6)
                .addComponent(new Simple6Component())
                .ExitAnimId(android.R.anim.fade_out)
                .Corner(20)
                .build();
    }


    @Override
    public void setListener() {

        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGuideView1();
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    public int getToolBarResId() {
        return 0;
    }

    @Override
    public void initToolBar(ToolBarManager navigationBarMgr) {

    }

}
