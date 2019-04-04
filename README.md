# EasyPopup「暂停维护」
[![](https://jitpack.io/v/zyyoona7/EasyPopup.svg)](https://jitpack.io/#zyyoona7/EasyPopup)

### PopupWindow

对 PopupWindow 的封装，使得在项目中使用起来更加简单、方便、快捷

### 项目特性

- 链式调用：除了在传统的 PopupWindow 使用方法之外还加入了更多的方法
- 带有相对于 AnchorView 的各个方位弹出的方法，弹出 PopupWindow 更轻松、更简单
- 支持 PopupWindow 弹出时背景变暗、指定 ViewGroup 背景变暗、设置变暗颜色等 (API>=18)
- 加入了简单的生命周期方法，自定义 PopupWindow、处理逻辑更方便、更清晰

### 效果图

![EasyPopup](https://github.com/zyyoona7/EasyPopup/blob/master/images/easy_popup.gif)

### 仓库依赖

Step 1. Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
    repositories {
	//...
	maven { url 'https://jitpack.io' }
    }
}
```
Step 2. Add the dependency
```gradle
dependencies {
    compile 'com.github.zyyoona7:EasyPopup:VERSION_CODE'
}
```
最新的[VERSION_CODE](https://github.com/zyyoona7/EasyPopup/releases)

### 使用

#### 1. 基本使用

**创建 EasyPopup 对象**

可以调用 setXxx() 方法进行属性设置，最后调用 createPopup() 方法实现对PopupWindow的初始化。

```java
private EasyPopup mCirclePop;
mCirclePop = EasyPopup.create()
        .setContentView(this, R.layout.layout_circle_comment)
        .setAnimationStyle(R.style.RightPopAnim)
  	//是否允许点击PopupWindow之外的地方消失
        .setFocusAndOutsideEnable(true)
        .apply();
```

**初始化 View**

可以调用 findViewById() 方法来获取 View 对象。

```java
TextView tvZan=mCirclePop.findViewById(R.id.tv_zan);
TextView tvComment=mCirclePop.findViewById(R.id.tv_comment);
tvZan.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ToastUtils.showShort("赞");
        mCirclePop.dismiss();
    }
});

tvComment.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ToastUtils.showShort("评论");
        mCirclePop.dismiss();
    }
});
```

**显示**

相对于 view 位置显示

```java
/**
 * 相对anchor view显示，适用 宽高不为match_parent
 *
 * @param anchor
 * @param yGravity  垂直方向的对齐方式
 * @param xGravity  水平方向的对齐方式
 * @param x            水平方向的偏移
 * @param y            垂直方向的偏移
 */
mCirclePop.showAtAnchorView(view, YGravity.CENTER, XGravity.LEFT, 0, 0);
```

除了 showAtAnchorView() 方法，内部还保留了 showAsDropDown()、showAtLocation() 方法。

**注意：如果使用 YGravity 和 XGravity 时，请确保使用之后 PopupWindow 没有超出屏幕边界，如果超出屏幕边界，YGravity 和 XGravity 可能无效，从而达不到你想要的效果。**[#4](https://github.com/zyyoona7/EasyPopup/issues/4)

**方位注解介绍**

垂直方向对齐：YGravity

```java
YGravity.CENTER,//垂直居中
YGravity.ABOVE,//anchor view之上
YGravity.BELOW,//anchor view之下
YGravity.ALIGN_TOP,//与anchor view顶部对齐
YGravity.ALIGN_BOTTOM,//anchor view底部对齐
```

水平方向对齐：XGravity

```java
XGravity.CENTER,//水平居中
XGravity.LEFT,//anchor view左侧
XGravity.RIGHT,//anchor view右侧
XGravity.ALIGN_LEFT,//与anchor view左边对齐
XGravity.ALIGN_RIGHT,//与anchor view右边对齐
```

#### 2. 弹出 PopupWindow 并伴随背景变暗

```java
mCirclePop = EasyPopup.create()
        .setContentView(this, R.layout.layout_circle_comment)
        .setAnimationStyle(R.style.RightPopAnim)
  	//是否允许点击PopupWindow之外的地方消失
        .setFocusAndOutsideEnable(true)
  	//允许背景变暗
  	.setBackgroundDimEnable(true)
  	//变暗的透明度(0-1)，0为完全透明
        .setDimValue(0.4f)
  	//变暗的背景颜色
  	.setDimColor(Color.YELLOW)
  	//指定任意 ViewGroup 背景变暗
  	.setDimView(viewGroup)
        .apply();
```

备注：背景变暗效果只支持 4.2 以上的版本。

#### 3. 点击 PopupWindow 之外的地方不让其消失

```java
mCirclePop = EasyPopup.create()
        .setContentView(this, R.layout.layout_circle_comment)
        .setAnimationStyle(R.style.RightPopAnim)
  	//是否允许点击PopupWindow之外的地方消失，
  	//设置为false点击之外的地方不会消失，但是会响应返回按钮事件
        .setFocusAndOutsideEnable(false)
        .apply();
```

#### 4. 自定义 PopupWindow

EasyPopup中自定义了三个生命周期：

- onPopupWindowCreated()：PopupWindow 对象初始化之后调用
- onPopupWindowViewCreated(View contentView)：PopupWindow 设置完 contentView 和宽高之后调用
- onPopupWindowDismiss()：PopupWindow dismiss 时调用

自定义 PopupWindow 需继承 BasePopup 抽象类，实现内部的两个抽象方法：

- initAttributes()：可以在此方法中设置 PopupWindow 需要的属性，该方法在 onPopupWindowCreated() 中调用
- initViews()：在此方法中初始化 view，该方法在 onPopupWindowViewCreated(View contentView) 中调用

**示例**

```java
public class ComplexPopup extends BasePopup<ComplexPopup> {
    private static final String TAG = "ComplexPopup";

    private Button mOkBtn;
    private Button mCancelBtn;

    public static ComplexPopup create(Context context){
        return new ComplexPopup(context);
    }

    protected ComplexPopup(Context context) {
        setContext(context);
    }


    @Override
    protected void initAttributes() {
        setContentView(R.layout.layout_complex, ViewGroup.LayoutParams.MATCH_PARENT, SizeUtils.dp2px(300));
        setFocusAndOutsideEnable(false)
                .setBackgroundDimEnable(true)
                .setDimValue(0.5f);
		//setXxx() 方法
    }

    @Override
    protected void initViews(View view) {
        mOkBtn = findViewById(R.id.btn_ok);
        mCancelBtn = findViewById(R.id.btn_cancel);

        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
```

```java
mComplexPopup = ComplexPopup.create(this)
	   .setDimView(mComplexBgDimView)
           .createPopup();
```

#### 5. 其他方法介绍 

| 方法名                                      | 作用                    | 备注        |
| :--------------------------------------- | --------------------- | --------- |
| setContentView(View contentView)         | 设置 contentView        |           |
| setContentView(Context context, @LayoutRes int layoutId) | 设置 contentView        |           |
| setWidth(int width)                      | 设置宽                   |           |
| setHeight(int height)                    | 设置高                   |           |
| setAnchorView(View view)                 | 设置目标 view             |           |
| setYGravity(@YGravity int yGravity)      | 设置垂直方向对齐              |           |
| setXGravity(@XGravity int xGravity)      | 设置水平方向对齐              |           |
| setOffsetX(int offsetX)                  | 设置水平偏移                |           |
| setOffsetY(int offsetY)                  | 设置垂直                  |           |
| setAnimationStyle(@StyleRes int animationStyle) | 设置动画风格                |           |
| getContentView()                         | 获取PopupWindow中加载的view | @Nullable |
| getContext()                             | 获取context             | @Nullable |
| getPopupWindow()                         | 获取PopupWindow对象       | @Nullable |
| dismiss()                                | 消失                    |           |

#### 6.版本迁移

在最新的 1.1.0 版本中对代码结构进行了跳转，在之前的基础上优化了泛型的继承，使得链式调用更加的顺畅；另外对 EasyPopup 继承使用也做了优化；对部分方法的命名也做了调整。

**i.继承使用修改、命名修改**

- 自定义 PopupWindow 时由原来的继承 **BaseCustomPopup** 改为继承  **BasePopup<T>** （具体使用请查看demo）。
- 将原来的 createPopup() 方法更名为 apply() 方法，新版中 apply() 方法不强制调用，在 showXxx() 方法中会检查，如果忘了调用 apply() 方法会主动调用一次。
- 将原来的 VerticalGravity、HorizontalGravity 注解更名为 YGravity、XGravity 精简了许多。
- 将原来的 getView() 方法更名为 findViewById()。

**ii.其他用法调整**

- 无论是自定义 PopupWindow 还是调用 EasyPopup 现在在构造方法中不在强制传入 Context 对象了，因为只有在设置 contentView 时传入了 layoutRes 才需要 Context 对象。如果你设置布局的方式是上述方式则需要手动设置 Context 对象：setContext(Context context)/setContentView(Context context, @LayoutRes int layoutId) 方法。
- 直接使用 EasyPopup 时提供了静态方法 create()/create(Context context) 方法创建对象，这样用起来比较酷。
- 加入了更多的方法，欢迎阅读源码。

### TODO


### 感谢

**[RelativePopupWindow](https://github.com/kakajika/RelativePopupWindow)**<br>
**[CustomPopwindow](https://github.com/pinguo-zhouwei/CustomPopwindow)**<br>
**[android-simple-tooltip](https://github.com/douglasjunior/android-simple-tooltip)**<br>
**[EasyDialog](https://github.com/tianzhijiexian/EasyDialog/tree/master/lib)**<br>
**[Android弹窗_PopupWindow详解](http://liangjingkanji.coding.me/2017/02/11/PopupWindow/)**<br>
### License

```
Copyright 2017 zyyoona7

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
