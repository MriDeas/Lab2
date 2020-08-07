# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.dan.learn.lab2.utils.* # 一颗星表示该包下的类不会混淆，子包下的类会被混淆 ， **表示子包下的类也会被混淆
#-keep class com.dan.learn.lab2.utils.*{*;} #保持该包下的类和类的成员不被混淆
#-keep class com.dan.learn.lab2.utils.**{*;} #保持该包下及子包下的类和类的成员不被混淆
-keep class com.dan.learn.lab2.DragListAdapter{*;} # 保持该类和类的内部成员不会被混淆
-keep public class * extends android.app.Activity # 使继承自Activity的类名不会被混淆
-keep public class * extends androidx.fragment.app.Fragment
-keep public class * extends androidx.android.app.Service
-keep public class * extends android.view.View

-keepclassmembers class com.dan.learn.lab2.test.TestProguardClass$ProguardSubClass{ # 该方法不会保留该类的名称，只保留该类内部的特定成员
   public *; # 保留该类下的所有公有成员
}
# -keep 方法会保持类名，防止被移除或重命名             -keepnames 防止类重命名
# -keepclassmembers方法会保持该类内部特定成员         -keepclassmembernames 防止类成员重命名
# -keepclasseswithmembers 保留类和某成员            -keepclasseswithmembernames 防止类和某成员重命名

#-keep com.dan.learn.lab2.test.TestProguardClass$ProguardSub2Class{
#   public <methods>; #保留该类下所有公有函数
#   private <init>; #保留私有构造函数
#   public <init>(String); #保留String参数的构造函数
#}

-keep class * implements Android.os.Parcelable{ #保持Parcelable不被混淆
   public static final Android.os.Parcelable$Creator *;
}

# 枚举关键函数需要保留
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# JNI函数不能混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

# 反射 只保持用到的类名和方法，不用讲反射到的类全部保持

# 关闭优化 字节码级别优化，应用运行更快
#-dontoptimize

# 关闭压缩，不再移除未使用的类和成员，发生在字节码优化之后
-dontshrink

# 关闭混淆 默认开启，随机重命名类和成员的名字
#-dontobfuscate
















