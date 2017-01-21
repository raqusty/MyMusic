package music.hayasi.android.com.mymusic.common.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class ImageLoaderImpl implements IImageSdk {
    private static ImageLoader mImageLoader;


    @Override
    public void initImageSDK(Context context) {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
//			// 你可以设置你自己实现的内存缓存
//			.memoryCache(new LruMemoryCache(2 * 1024 * 1024/4))
                // 为了减少OOM几率而设置弱引用的内存缓存
                .memoryCache(new WeakMemoryCache())
//			/**
//		     * 为位图最大内存缓存大小(以字节为单位),默认值,可用应用程序内存的1/8
//		     * 注意:如果你使用这个方法,那么LruMemoryCache将被用作内存缓存。
//		     * 您可以使用memoryCache(MemoryCacheAware)方法来设置自己的MemoryCacheAware的实现。
//		     */
//			.memoryCacheSize(2 * 1024 * 1024/4)
//			 设置磁盘缓存的路径，暂时不设置的原因是，如果卸载应用的话，这些缓存数据将不会被删除
//			.diskCache(new UnlimitedDiscCache(cacheDir))
                // 设置不允许一张图片缓存在内存中多个大小， 默认是允许的,(同一个图片URL)根据不同大小的ImageView保存不同大小图片
                .denyCacheImageMultipleSizesInMemory()
                // 设置磁盘缓存的文件名称是MD5
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                // 设置磁盘缓存大小是100Mb
                .diskCacheSize(100 * 1024 * 1024)
                // 设置磁盘缓存的最多文件数是100
                .diskCacheFileCount(100)
                // 设置用于显示图片的线程池大小
                .threadPoolSize(3)
                // 设置当前线程的优先级
                .threadPriority(Thread.NORM_PRIORITY - 2)
                // 设置用于加载和显示图像的队列类型
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                // connectTimeout (60 s), readTimeout (60 s)超时时间
                .imageDownloader(new BaseImageDownloader(context, 60 * 1000, 60 * 1000))
//			// remove for release app
//			.writeDebugLogs()
                .build();

        mImageLoader = ImageLoader.getInstance();
        mImageLoader.init(configuration);
    }

    @Override
    public void displayImage(int imageSouce, String url, final ImageView mImageView) {
        DisplayImageOptions.Builder options =  getDisplayOptionsBuilder();
        mImageLoader.loadImage(url, options.build(), new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view,
                                        FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                mImageView.setImageBitmap(loadedImage);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
    }

    private static DisplayImageOptions.Builder getDisplayOptionsBuilder() {

//        BitmapDisplayer displayer = null;
//        if (options.getCornerRadiusPixels() == null || options.getCornerRadiusPixels() == 0) {
//            // 如果null或者0，那么就正常显示
//            displayer = new SimpleBitmapDisplayer();
//        } else if (options.getCornerRadiusPixels() > 0) {
//            // 如果大于0，那么就显示圆角图片
//            displayer = new RoundedBitmapDisplayer(options.getCornerRadiusPixels());
//        } else {
//            // 如果小于0，那么就显示方形图片
//            displayer = new SquareBitmapDisplayer(-options.getCornerRadiusPixels());
//        }

        return new DisplayImageOptions.Builder()
                .considerExifParams(true)
                // 设置RGB值低一点，降低资源的消耗
                .bitmapConfig(Bitmap.Config.RGB_565)
                /*
                 *  图片显示方式：
				 *  RoundedBitmapDisplayer（int roundPixels）设置圆角图片
		         *  FakeBitmapDisplayer（）这个类什么都没做
		         *  FadeInBitmapDisplayer（int durationMillis）设置图片渐显的时间
		         *　SimpleBitmapDisplayer()正常显示一张图片
				 */
//                .displayer(displayer)
                // 设置图片在下载期间显示的图片
//                .showImageOnLoading(options.getLoadingImgResID() == null ? 0 : options.getLoadingImgResID())
//                // 设置图片加载/解码过程中错误时候显示的图片
//                .showImageOnFail(options.getErrorImgResID() == null ? 0 : options.getErrorImgResID())
//                // 设置图片Uri为空或是错误的时候显示的图片
//                .showImageForEmptyUri(options.getEmptyImgResID() == null ? 0 : options.getEmptyImgResID())
                // 设置缓存到内存中
                .cacheInMemory(true)
                // 设置是否缓存到本地磁盘
//                .cacheOnDisk(options.isCacheOnDisk())
                ;
    }
}