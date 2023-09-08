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

-keepclassmembers class  com.zappstudio.data.model.*{
   public *;
}


-keepnames class androidx.lifecycle.ViewModel
-keepclassmembers class * extends androidx.lifecycle.ViewModel { <init>(...); }
-keepclassmembers class * implements androidx.lifecycle.LifecycleObserver { <init>(...); }
-keepclassmembers class * implements androidx.lifecycle.LifecycleOwner { <init>(...); }
-keepclassmembers class androidx.lifecycle.Lifecycle$State { *; }
-keepclassmembers class androidx.lifecycle.Lifecycle$Event { *; }
-keep class * implements androidx.lifecycle.LifecycleOwner { public <init>(...); }
-keep class * implements androidx.lifecycle.LifecycleObserver { public <init>(...); }
-keepclassmembers class * { public <init>(...); }

-keepclassmembers class com.zappstudio.beatbest.di.*{
   public *;
}

-keepclassmembers class com.zappstudio.beatbest.register.di.*{
   public *;
}

-keepclassmembers class com.zappstudio.splash.di.*{
   public *;
}

-keepclassmembers class com.zappstudio.core.app.di.*{
   public *;
}

-keepclassmembers class com.zappstudio.data.di.*{
   public *;
}


-keepnames class android.arch.lifecycle.ViewModel
-keepclassmembers public class * extends android.arch.lifecycle.ViewModel { public <init>(...); }
-keepclassmembers class * { public <init>(...); }

-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

-keepclasseswithmembers class * {
    @com.squareup.moshi.* <methods>;
}

-keep @com.squareup.moshi.JsonQualifier @interface *

# Enum field names are used by the integrated EnumJsonAdapter.
# values() is synthesized by the Kotlin compiler and is used by EnumJsonAdapter indirectly
# Annotate enums with @JsonClass(generateAdapter = false) to use them with Moshi.
-keepclassmembers @com.squareup.moshi.JsonClass class * extends java.lang.Enum {
    <fields>;
    **[] values();
}

# Keep helper method to avoid R8 optimisation that would keep all Kotlin Metadata when unwanted
-keepclassmembers class com.squareup.moshi.internal.Util {
    private static java.lang.String getKotlinMetadataClassName();
}

# Keep ToJson/FromJson-annotated methods
-keepclassmembers class * {
  @com.squareup.moshi.FromJson <methods>;
  @com.squareup.moshi.ToJson <methods>;
}

-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation