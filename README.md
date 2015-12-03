# RippleEffect
RippleEffect Follow Material Design
  ![image](https://github.com/CodingForAndroid/RippleEffect/blob/master/screenshot/ripple%20effect.gif)
 
 
 instruction:
 ①：要实现水波纹效果，首先这个View 必须是可点击的，也就是说clickable ：true 才可以触发 比如默认 Button 的Clickable =true，TextView, ImageView =false，但是可以手动设置 true。
 
②：这个是一个布局、可以包裹 需要实现水波纹效果的 view，任何View 只要是可点击的，都可以包裹进来。

③：可以保证，当手指按下在当前View 上，如果手滑动出了当前View，不会触发该 点击事件， 也就是只有手按下，和手抬起，都是同一个View 才触发 点击事件。

④：这个效果，保证是 水波纹 结束以后，再去响应 View的点击事件 。

⑤：一个布局 包裹这么多View，怎么区分每一个的点击事件呢？ 答： 根据 每个View 的id 去响应不同的 事件。

Usage
include this library, use
可以将下面的 拷贝到layout xml布局中
    <com.jorge.ripple_effect.RippleLayout
        android:id="@+id/ripplelayout"
        android:orientation="vertical"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content" >

        <Button
            android:layout_marginTop="30dp"
            android:id="@+id/button01"
            android:layout_width="fill_parent"
            android:layout_height="fill_parent"
            android:background="@drawable/shape_rectangle_regist_button_selector"
            android:text="Activity跳转"

            android:enabled="true"/>
        <Button
            android:layout_marginTop="50dp"
            android:id="@+id/button02"
            android:layout_width="fill_parent"
            android:layout_height="fill_parent"
            android:background="@drawable/shape_rectangle_regist_button_selector"
            android:text="吐司"

            android:enabled="true"/>
        <Button
            android:layout_gravity="center_horizontal"
            android:layout_marginTop="50dp"
            android:id="@+id/button03"
            android:layout_width="300dp"
            android:layout_height="wrap_content"
            android:background="@drawable/shape_rectangle_regist_button_selector2"
            android:text="Button"
            android:enabled="true"/>
        <ImageButton
            android:src="@drawable/shape_rectangle_regist_button_selector"
            android:background="@null"
            android:layout_gravity="center_horizontal"
            android:layout_marginTop="50dp"
            android:id="@+id/button04"
            android:layout_width="300dp"
            android:layout_height="50dp"
            android:enabled="true"/>
        <TextView
            android:background="@android:color/white"
            android:gravity="center"
            android:text="TextView"
            android:layout_height="60dp"
            android:layout_width="fill_parent"
            android:layout_margin="10dp"/>
    </com.jorge.ripple_effect.RippleLayout>
    
    
    
    
    
    
    
   
    
    Download  for  android studio
  
   用Gradle 去加载：
    
dependencies {
    compile 'RippleEffect:library:1.0.0'
 }
