
-keep class com.endava.ui_designview.accordion.** { *; }
-keep class com.endava.ui_designview.avatar.** { *; }

-keepclassmembers class com.endava.ui_designview.avatar.AvatarView {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public <init>(android.content.Context, android.util.AttributeSet, int, int);
}

-keepattributes *Annotation*, InnerClasses, EnclosingMethod