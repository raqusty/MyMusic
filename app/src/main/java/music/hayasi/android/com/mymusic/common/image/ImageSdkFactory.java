package music.hayasi.android.com.mymusic.common.image;

public class ImageSdkFactory {

    public IImageSdk createIImageSdk() {
        return new ImageLoaderImpl();
    }

}
