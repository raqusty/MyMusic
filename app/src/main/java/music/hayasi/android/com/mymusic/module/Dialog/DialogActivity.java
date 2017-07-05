package music.hayasi.android.com.mymusic.module.Dialog;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.common.dialog.OnSureOrCancelListener;
import music.hayasi.android.com.mymusic.common.dialog.SureOrCancelWithCustomTipsDialog;
import music.hayasi.android.com.mymusic.databinding.AnimActivityBinding;
import music.hayasi.android.com.mymusic.module.MaterialDesign.DesignActivity;

public class DialogActivity extends BaseActivity {

    private AnimActivityBinding binding;

    Animation scaleAnimation;

    @Override
    protected int getContentViewResId() {
        return 0;
    }

    @Override
    public void initViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.anim_activity);

        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_1);
        binding.button3.setText("ValueAnimator");
        binding.button1.setText("startAnimation");
        binding.button2.setText("ObjectAnimator");

        ValueAnimator a;
        ObjectAnimator b;
        AnimatorSet s;
        TimeAnimator f;

        binding.button3.setText(Html.fromHtml(
                "<b>text3:</b>  Text with a " +
                        "<a href=\"http://www.baidu.com\">link</a> " +
                        "created in the Java source code using HTML."));

        binding.button3.setMovementMethod(LinkMovementMethod.getInstance());  //其实就这一句是关键


        binding.bbb.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = binding.bbb.getRootView().getHeight() - binding.bbb.getHeight();
                Log.i("linzehao", "root  " + binding.bbb.getRootView().getHeight());
                Log.i("linzehao", "my " + binding.bbb.getHeight());
                if (heightDiff > 200) { //高度变小200像素则认为键盘弹出
                    //这里执行需要的处理
                    Log.i("linzehao", "show");
                } else {
                    Log.i("linzehao", "hide");
                }
            }
        });

    }


    @Override
    public void setListener() {
        final SureOrCancelWithCustomTipsDialog dialog = new SureOrCancelWithCustomTipsDialog(mContext, "测试", new OnSureOrCancelListener() {
            @Override
            public void onCancel() {
                Log.i("linzehao", "取消");
            }

            @Override
            public void onSure() {
                Log.i("linzehao", "确定");
            }
        });

        dialog.getWindow().setGravity(Gravity.BOTTOM);


        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SureOrCancelWithCustomTipsDialog dialog1 = null;
                dialog1.show();
                dialog.show();
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                binding.textview1.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//                imm.showSoftInput(binding.textview1, 0);
            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
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
