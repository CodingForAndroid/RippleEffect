# RippleEffect
RippleEffect Follow Material Design
  ![image](https://github.com/CodingForAndroid/RippleEffect/blob/master/screenshot/ripple%20effect.gif)
 
Usage
include this library, use

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
    
    Gradle: 
    
dependencies {
    compile 'RippleEffect:library:1.0.0'
 }
