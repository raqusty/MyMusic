package music.hayasi.android.com.mymusic.common.image;

import android.content.Context;
import android.widget.ImageView;

public class ImageUtil {

    private static IImageSdk imageSdkImpl = new ImageSdkFactory().createIImageSdk();

    public static void initImageLoader(Context context) {
        imageSdkImpl.initImageSDK(context);
    }

    public static void displayImage(int imageSouce, String url, ImageView mImageView) {
        imageSdkImpl.displayImage(imageSouce, url, mImageView);
    }
}