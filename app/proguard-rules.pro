# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\mysoftware\androidstudio\as_sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#原则：涉及到反射（webview的js交互，注解，Gson交互，xml文件等等的标签）的绝对不能混淆，已混淆过的，绝对不能混淆。（android应该是只要开启了混淆就会默认混淆的，只要在下面过滤掉不混淆的就好了）
#android实际上已经在proguard-android.txt中默认设置了很多混淆规则了，对于基本的混淆设置，以及java、android的一些基本保护也已经设置了，
#所以你要做的就是对自己的代码以及引入的第三方决定要保护哪一些代码

#下面这部分是android没保护的，这里保护一下，所有的module都复制这部分过去
#------assets相关包，说实话我不在确定在这个混淆的原因是什么。有知道的可以告知一下
-dontwarn assets.**
-keep class assets.** {
  *;
}

#-----不混淆v4、v7包
-dontwarn android.support.**
-keep class android.support.** { *; }
-keep interface android.support.** { *; }

#不混淆WebView相关类
-keep class android.net.http.SslError
-keep class android.webkit.**{*;}

#自定义控件及组件不要打包混淆
#如果我们自定了ListView,ScrollView,Gallery这些组件的时候，我们就不能混淆这些自定义的类了，因为在layout里面我们已经引用这个了类，而且已经写死了。同样四大组件也是不能打包混淆的
#四大组件不能混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application {*;}
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference

#自定义控件不要混淆
-keep public class * extends android.view.View {*;}

#数据适配器adapter不要混淆
#adapter也不能混淆
-keep public class * extends android.widget.BaseAdapter {*;}

#如果你使用了CusorAdapter,添加下面这行
-keep public class * extends android.widget.CusorAdapter{*;}
#同样如果你觉得麻烦，就直接将BaseAdpater换成Adapter
#数据模型不要混淆
-keepnames class * implements java.io.Serializable	#比如我们要向activity传递对象使用了Serializable接口的时候，这时候这个类及类里面#的所有内容都不能混淆
-keepclassmembers class * implements java.io.Serializable {*;}

#（下面两部分对应着修改，第三方的框架的混淆可以去百度或者官网找，自己的类的话根据实际情况（主要是涉及反射的js交互，注解，Gson交互，xml文件等等的标签相关）来混淆，下面这部分是原有奕报告的，到时候要修改）
#--------------------------------第三方框架
#混淆ButterKnife
-dontwarn butterknife.internal.**
-keep class butterknife.** { *; }
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#混淆universal-image-loader
-keep class com.nostra13.universalimageloader.** { *; }

#混淆友盟统计和友盟用户反馈
#这是由于SDK中的部分代码使用反射来调用构造函数， 如果被混淆掉， 在运行时会提示"NoSuchMethod"错误
-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}

#自己添加的友盟混淆代码
-keep class com.umeng.** { *; }

#混淆融云（现在引用的版本的混淆）
-keepattributes Exceptions,InnerClasses

-keepattributes Signature

# RongCloud SDK
-keep class io.rong.** {*;}
-keep class * implements io.rong.imlib.model.MessageContent {*;}
-dontwarn io.rong.push.**
-dontnote com.xiaomi.**
-dontnote com.google.android.gms.gcm.**
-dontnote io.rong.**
# VoIP
-keep class io.agora.rtc.** {*;}
# Location
-keep class com.amap.api.**{*;}
-keep class com.amap.api.services.**{*;}
-ignorewarnings
#自定义的融云通知接收者也不混淆
-keep class com.lysoft.android.lyyd.report.im.rongyun.RYNotificationReceiver {*;}

#混淆ShareSDK分享
-keep class cn.sharesdk.** { *; }

#混淆ShareSDK短信服务
-keep class cn.smssdk.**{ *; }
-keep class com.mob.**{ *; }
-keep class m.framework.**{ *; }

#混淆信鸽
-keep class com.tencent.** { *; }
-keep class com.tencent.android.tpush.**  { *; }
-keep class com.tencent.mid.**  { *; }

#混淆BugHD
#-keep class im.fir.** { *; }


#不混淆http post上传图片的jar包
-keep class org.apache.** { *; }

#不混淆http的jar包
-keep class org.apache.** { *; }

#不混淆nineoldandroids的jar包
-keep class com.nineoldandroids.** { *; }

#不混淆nineoldandroids的jar包
-keep class com.nineoldandroids.** { *; }

#不混淆ipush 相关
-keepattributes Exceptions,InnerClasses
-keep class com.google.gson.examples.android.model.** { *; }
-keep class org.glassfish.** {*;}
-keep class cn.com.impush.** {*;}
-keep class sun.misc.Unsafe { *; }
-keep class javax.xml.** { *; }
-keepattributes Signature
-keepattributes *Annotation*

#不混淆Gson 相关
-keepattributes Signature
-keep class com.google.gson.stream.** { *; }


#-----------------------------------自己的类
#另外，由于SDK需要引用导入工程的资源文件，通过了反射机制得到资源引用文件R.java，但是在开发者通过proguard等混淆/优化工具处理apk时，proguard可能会将R.java删除，如果遇到这个问题，请在proguard配置文件中添加keep命令如：
-keep public class com.lysoft.android.lyyd.report.R$*{
	public static final int *;
}


#----与js相关
#不混淆有提供给js调用的代码的页面
-keepattributes *JavascriptInterface*
-keepclassmembers class com.lysoft.android.lyyd.report.module.login.SchoolLoginActivity$SchoolLoginJsInteraction {
  public *;
}
-keepclassmembers class com.lysoft.android.lyyd.report.module.common.CommonJsInteraction {
  public *;
}
-keepclassmembers class com.lysoft.android.lyyd.report.module.main.report.BriefReportJsInteraction {
  public *;
}
-keepclassmembers class com.lysoft.android.lyyd.report.module.main.report.ReportPageFragment {
  public *;
}
-keepclassmembers class com.lysoft.android.lyyd.report.module.report.campusreport.CampusReportActivity {
  public *;
}
-keepclassmembers class com.lysoft.android.lyyd.report.module.report.consumereport.ConsumptionRankActivity {
  public *;
}
-keepclassmembers class com.lysoft.android.lyyd.report.module.report.consumereport.ConsumptionReportActivity {
  public *;
}
-keepclassmembers class com.lysoft.android.lyyd.report.module.report.readingreport.ReadingRankActivity {
  public *;
}
-keepclassmembers class com.lysoft.android.lyyd.report.module.report.studyreport.StudyRankActivity {
  public *;
}
-keepclassmembers class com.lysoft.android.lyyd.report.module.report.studyreport.StudyReportActivity {
  public *;
}

#-----与xml相关


#-----与GSON相关
#SingleCourse 这个类的数会与wear通信时用的，如果混淆了，就没有意义了
-keep class com.lysoft.android.lyyd.report.module.timetable.wear.SingleCourse
-keepclassmembers class com.lysoft.android.lyyd.report.module.timetable.wear.SingleCourse {*;}



#-----与注解相关



#-----实体类混淆
-keep class com.lysoft.android.lyyd.report.baselibrary.framework.common.interfaces.INotProguard
-keepnames class * implements com.lysoft.android.lyyd.report.baselibrary.framework.common.interfaces.INotProguard
-keepclassmembers class * implements com.lysoft.android.lyyd.report.baselibrary.framework.common.interfaces.INotProguard {*;}

#即使混淆后也能保证保留crash日志的行号
-keepattributes SourceFile,LineNumberTable

#不混淆openFileChooser，在低版本处理html的input标签
-keepclassmembers class * extends android.webkit.WebChromeClient{
       public void openFileChooser(...);
}

