package music.hayasi.android.com.mymusic.module.model;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import java.util.Hashtable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.databinding.AnimActivityBinding;
import music.hayasi.android.com.mymusic.module.model.Decorator.IStringDeal;
import music.hayasi.android.com.mymusic.module.model.Decorator.LeftStringDealDecorator;
import music.hayasi.android.com.mymusic.module.model.Decorator.RightStringDealDecorator;
import music.hayasi.android.com.mymusic.module.model.Decorator.StringComponent;
import music.hayasi.android.com.mymusic.module.model.Future.FutureData;
import music.hayasi.android.com.mymusic.module.model.Future.FutureDataJDK;
import music.hayasi.android.com.mymusic.module.model.Future.RealData;

public class ModelActivity extends BaseActivity {

    private AnimActivityBinding binding;


    @Override
    protected int getContentViewResId() {
        return 0;
    }

    @Override
    public void initViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.anim_activity);

        binding.button3.setText("Decorator");
        binding.button1.setText("Future");
        binding.button2.setText("FutureJdk");
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

                final FutureData futureData = new FutureData();
                new Thread() {
                    public void run() {
                        RealData realData = new RealData("asd  ");
                        futureData.setRealData(realData);
                        String ss = realData.getResult();
                        h.obtainMessage(1, ss).sendToTarget();

//                        Message msg = Message.obtain();
//                        msg.what = 100;
//                        msg.obj = "asdfghjkl";
//                        handler.sendMessage(msg);
                    }
                }.start();


            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FutureTask<String> future = new FutureTask<String>(new FutureDataJDK("sf "));
                ExecutorService executorService = Executors.newFixedThreadPool(1);
                executorService.submit(future);
                try {
                    h.obtainMessage(1,  future.get().toString()).sendToTarget();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                Hashtable<Integer,String> s = new Hashtable<Integer, String>();
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

    Handler h = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    binding.textview1.setText((String) msg.obj);
            }
        }


    };

}
