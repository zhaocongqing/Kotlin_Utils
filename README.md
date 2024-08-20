#### 工具库功能说明

- `lib_tools` 是一个工具库，提供了一些常用的工具函数，方便开发者使用。

###### 一、 常用工具类
1. `ClipboardUtils`：剪贴板相关工具类
2. `DESUtils`：DES加密解密工具类
3. `LiveDataEventBus`：LiveDataEventBus事件总线
4. `EncodeUtils`：编码解码相关工具类
5. `JSONUtils`：Json(google.gson)相关工具类
6. `MD5`：MD5加密工具类
7. `ToastUtils`：吐司工具类
8. `Immersion.kt`：状态栏工具类
9. `ClickUtils`：防抖动点击工具类
10. `FleTextView`：可灵活代替Button的自定义TextView
11. `ColorfulTextView`：自定义多样式TextView
12. `NetworkUtils`：网络工具类(判断网络是否可用、是否wifi、是否移动网络等)
13. `ScreenUtils`：屏幕工具类(获取屏幕宽高、屏幕密度等)
14. `AppIsInstalledUtils`：判断应用是否安装工具类
15. `RomUtils`: 判断手机系统工具类


###### 二、 使用方式

###### 1.自定义TextView

```xml
<com.learning.tools.widget.FleTextView
    android:id="@+id/btn_login"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toEndOf="@id/tv_login_prompt"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:paddingStart="9dp"
    android:paddingEnd="9dp"
    android:paddingTop="4dp"
    android:paddingBottom="4dp"
    app:background_normal="@color/theme"
    app:background_pressed="@color/theme"
    app:background_unable="@color/theme"
    app:text_color_normal="@color/white"
    app:text_color_pressed="@color/white"
    app:text_color_unable="@color/white"
    app:corner_radius="20dp"
    android:gravity="center"
    android:text="@string/home_login"
    android:textSize="14sp"
    android:textStyle="bold"/>
```

FleTextView示例图：

<img src="https://file.40017.cn/groundtrafficstage/hopegoo/202404251506001.png" width="210px">

```xml
<com.learning.tools.widget.ColorfulTextView
    android:id="@+id/tv_ful"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toEndOf="@id/tv_login_prompt"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"/>
```
```kotlin
  tvFul.clear()
    .appendText("Hello", ContextCompat.getColor(this, R.color.design_theme), 16, true)
    .appendText("World!", ContextCompat.getColor(this, R.color.design_warning_red), 15, false)
    .appendText("Debug", ContextCompat.getColor(this, R.color.design_primary), 12, true)
    .appendText("Test!", ContextCompat.getColor(this, R.color.design_theme), 18, true)
```

ColorfulTextView示例图：

<img src="https://file.40017.cn/groundtrafficstage/hopegoo/202404171442.png" width="210px">