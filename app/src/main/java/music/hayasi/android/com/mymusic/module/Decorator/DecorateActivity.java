package music.hayasi.android.com.mymusic.module.Decorator;

import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.databinding.AnimActivityBinding;

public class DecorateActivity extends BaseActivity {

    private AnimActivityBinding binding;



    @Override
    protected int getContentViewResId() {
        return 0;
    }

    @Override
    public void initViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.anim_activity);

        binding.button3.setText("ValueAnimator");
        binding.button1.setText("startAnimation");
        binding.button2.setText("ObjectAnimator");
    }


    @Override
    public void setListener() {
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IStringDeal a = new LeftStringDealDecorator(
                        new RightStringDealDecorator(
                                new StringComponent()));
                binding.textview1.setText(a.handleString());

            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Writer writer = new BufferedWriter(new FileWriter(new File("file.text")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s ="sdf";
                s.substring(1,2);
                ArrayList<String> s1;
                Vector<String> s11;
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
