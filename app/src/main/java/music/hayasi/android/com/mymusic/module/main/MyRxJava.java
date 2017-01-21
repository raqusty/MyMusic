package music.hayasi.android.com.mymusic.module.main;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MyRxJava extends BaseActivity {

    @Bind(R.id.text2)
    TextView mMultiStateView;
    @Bind(R.id.text1)
    TextView mTextView;

    @Override
    protected int getContentViewResId() {
        return R.layout.my_rx_java;
    }


    @Override
    public void initViews() {

        text2();


    }

    //操作符操作
    private void text2() {
//        String[] students = {"87678", "23", "654764", "4534", "3213"};
        List<List<String>> students = new ArrayList<>();
        List<String> strlist = null;
        for (int i = 0; i < 4; i++) {
            strlist = new ArrayList<String>();
            for (int j = 0; j < 4; j++) {
                if (i == 0 || j == 0) {
                    strlist.add(null);
                } else {
                    strlist.add(i + " " + j);
                }
            }
            students.add(strlist);
        }

        Observable.from(students)
                .flatMap(new Func1<List<String>, Observable<String>>() {
                    @Override
                    public Observable<String> call(List<String> url) {
                        return Observable.from(url);
                    }
                })
                .take(15)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        if (s == null) return false;
                        return true;
                    }
                })

                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String name) {
                        Log.d("linzehao", name);
                    }
                });
    }

    //线程操作
    private void text1() {
        String[] students = {"87678", "23", "654764", "4534", "3213"};
        Observable.create(new Observable.OnSubscribe<Object>() {
            public void call(Subscriber s) {

            }
        });
        Observable.from(students)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        String str = "  my2" + s;
                        return str;
                    }
                })
                .subscribeOn(Schedulers.newThread())//事件在新线程产生
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        String str = "my  1" + s;
                        return str;
                    }
                })
                .subscribeOn(Schedulers.newThread())//事件在新线程产生
                .observeOn(AndroidSchedulers.mainThread()) // 事件在主线程消费
//                .subscribe(new Action1<String>() {
//
//                    @Override
//                    public void call(String s) {
//                        Log.d("linzehao", s);
//                    }
//                });
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String name) {
                        Log.d("linzehao", name);
                    }
                });
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

    @Override
    public Toolbar setToolBar() {
        return null;
    }
}
