package music.hayasi.android.com.mymusic.module.NetWork;

import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.common.myNet.Uitls.ModelUitl;
import music.hayasi.android.com.mymusic.common.myNet.callback.StringCallback;
import music.hayasi.android.com.mymusic.databinding.NetActivityBinding;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetActivity extends BaseActivity {

    private NetActivityBinding binding;

    @Override
    protected int getContentViewResId() {
        return 0;
    }

    @Override
    public void initViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.net_activity);

    }

    @Override
    public void setListener() {
        binding.text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        binding.text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelUitl.getStringModel("https://www.baidu.com", null, new StringCallback() {

                    @Override
                    public void onSuccess(String data, Call call, Response response) {
                        binding.text2.setText(data);
                    }

                    @Override
                    public void onFail(Call call, Response response, Exception e) {
                        binding.text2.setText(e.getMessage());
                    }

                });

            }
        });
        binding.text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("https://www.baidu.com")
                                .build();

                        Response response = null;
                        try {
                            response = client.newCall(request).execute();
                            Log.i("linzehao", response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
            }
        });

    }


    @Override
    public int getToolBarResId() {
        return R.menu.main;
    }

    @Override
    public void initToolBar(ToolBarManager navigationBarMgr) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
