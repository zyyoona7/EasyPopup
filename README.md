# EasyPopup
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
mCirclePop = new EasyPopup(this)
        .setContentView(R.layout.layout_circle_comment)
        .setAnimationStyle(R.style.CirclePopAnim)
  	//是否允许点击PopupWindow之外的地方消失
        .setFocusAndOutsideEnable(true)
        .createPopup();
```

**初始化 View**

可以调用 getView() 方法来获取 View 对象。

```java
TextView tvZan=mCirclePop.getView(R.id.tv_zan);
TextView tvComment=mCirclePop.getView(R.id.tv_comment);
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
 * @param vertGravity  垂直方向的对齐方式
 * @param horizGravity 水平方向的对齐方式
 * @param x            水平方向的偏移
 * @param y            垂直方向的偏移
 */
mCirclePop.showAtAnchorView(view, VerticalGravity.CENTER, HorizontalGravity.LEFT, 0, 0);
```

除了 showAtAnchorView() 方法，内部还保留了 showAsDropDown()、showAtLocation() 方法。

**注意：如果使用 VerticalGravity 和 HorizontalGravity 时，请确保使用之后 PopupWindow 没有超出屏幕边界，如果超出屏幕边界，VerticalGravity 和 HorizontalGravity 可能无效，从而达不到你想要的效果。**[#4](https://github.com/zyyoona7/EasyPopup/issues/4)

**方位注解介绍**

垂直方向对齐：VerticalGravity

```java
VerticalGravity.CENTER,//垂直居中
VerticalGravity.ABOVE,//anchor view之上
VerticalGravity.BELOW,//anchor view之下
VerticalGravity.ALIGN_TOP,//与anchor view顶部对齐
VerticalGravity.ALIGN_BOTTOM,//anchor view底部对齐
```

水平方向对齐：HorizontalGravity

```java
HorizontalGravity.CENTER,//水平居中
HorizontalGravity.LEFT,//anchor view左侧
HorizontalGravity.RIGHT,//anchor view右侧
HorizontalGravity.ALIGN_LEFT,//与anchor view左边对齐
HorizontalGravity.ALIGN_RIGHT,//与anchor view右边对齐
```

#### 2. 弹出 PopupWindow 并伴随背景变暗

```java
mCirclePop = new EasyPopup(this)
        .setContentView(R.layout.layout_circle_comment)
        .setAnimationStyle(R.style.CirclePopAnim)
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
        .createPopup();
```

备注：背景变暗效果只支持 4.2 以上的版本。

#### 3. 点击 PopupWindow 之外的地方不让其消失

```java
mCirclePop = new EasyPopup(this)
        .setContentView(R.layout.layout_circle_comment)
        .setAnimationStyle(R.style.CirclePopAnim)
  	//是否允许点击PopupWindow之外的地方消失，
  	//设置为false点击之外的地方不会消失，但是会响应返回按钮事件
        .setFocusAndOutsideEnable(false)
        .createPopup();
```

#### 4. 自定义 PopupWindow

EasyPopup中自定义了三个生命周期：

- onPopupWindowCreated()：PopupWindow 对象初始化之后调用
- onPopupWindowViewCreated(View contentView)：PopupWindow 设置完 contentView 和宽高之后调用
- onPopupWindowDismiss()：PopupWindow dismiss 时调用

自定义 PopupWindow 需继承 BaseCustomPopup 抽象类，实现内部的两个抽象方法：

- initAttributes()：可以在此方法中设置 PopupWindow 需要的属性，该方法在 onPopupWindowCreated() 中调用
- initViews()：在此方法中初始化 view，该方法在 onPopupWindowViewCreated(View contentView) 中调用

**示例**

```java
public class ComplexPopup extends BaseCustomPopup {
    private static final String TAG = "ComplexPopup";

    private Button mOkBtn;
    private Button mCancelBtn;

    protected ComplexPopup(Context context) {
        super(context);
    }


    @Override
    protected void initAttributes() {
        setContentView(R.layout.layout_complex, 
                       ViewGroup.LayoutParams.MATCH_PARENT, SizeUtils.dp2px(300));
        setFocusAndOutsideEnable(false)
          	.setBackgroundDimEnable(true)
                .setDimValue(0.5f);
        //setXxx()
        //...
    }

    @Override
    protected void initViews(View view) {
        mOkBtn = getView(R.id.btn_ok);
        mCancelBtn = getView(R.id.btn_cancel);

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
mComplexPopup = new ComplexPopup(this);
mComplexPopup.setDimView(mComplexBgDimView)
           .createPopup();
```

#### 5. 其他方法介绍 

| 方法名                                      | 作用                    | 备注        |
| :--------------------------------------- | --------------------- | --------- |
| setContentView(View contentView)         | 设置 contentView        |           |
| setContentView(@LayoutRes int layoutId)  | 设置 contentView        |           |
| setWidth(int width)                      | 设置宽                   |           |
| setHeight(int height)                    | 设置高                   |           |
| setAnchorView(View view)                 | 设置目标 view             |           |
| setVerticalGravity(@VerticalGravity int verticalGravity) | 设置垂直方向对齐              |           |
| setHorizontalGravity(@VerticalGravity int horizontalGravity) | 设置水平方向对齐              |           |
| setOffsetX(int offsetX)                  | 设置水平偏移                |           |
| setOffsetY(int offsetY)                  | 设置垂直                  |           |
| setAnimationStyle(@StyleRes int animationStyle) | 设置动画风格                |           |
| getContentView()                         | 获取PopupWindow中加载的view | @Nullable |
| getContext()                             | 获取context             | @Nullable |
| getPopupWindow()                         | 获取PopupWindow对象       | @Nullable |
| dismiss()                                | 消失                    |           |

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
