package music.hayasi.android.com.mymusic.common.image;

import android.content.Context;
import android.widget.ImageView;

public interface IImageSdk {
    public void initImageSDK(Context context);

    public void displayImage(int imageSouce, String url, ImageView mImageView);

}