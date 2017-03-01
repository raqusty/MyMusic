package music.hayasi.android.com.mymusic.module.mvvm.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import music.hayasi.android.com.mymusic.databinding.MvvmItemBinding;
import music.hayasi.android.com.mymusic.module.mvvm.entity.User;

public class mvvmAdapter extends BaseAdapter {

    private List<User> mDataList;
    private LayoutInflater inflate;

    public mvvmAdapter(List<User> list, Context context) {
        mDataList = list;
        inflate = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MvvmItemBinding binding;
        if (convertView == null) {
            binding = MvvmItemBinding.inflate(inflate, parent, false);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }
        binding.setUser(mDataList.get(position));
        binding.setHandlers(new EventHandlers1());
        return binding.getRoot();
    }

    public class EventHandlers1 {
        public void handleClick(View view) {
            MvvmItemBinding binding = DataBindingUtil.getBinding(view);
            binding.getUser().setFirstName("ss");
//            User user = binding.getUser();
//            user.setFirstName("ff");
        }
//        public void ItemClick(AdapterView<?> parent, View view, int position, long id) {
//            mDataList.get(position).setFirstName(position + "ff");
//        }
    }
}
