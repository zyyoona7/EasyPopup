# PopupWindowUtil
##对popupWindow进行封装,用起来so easy
##项目不再维护  推荐[BasePopup](https://github.com/razerdp/BasePopup)

(无意在谷歌上搜到别人项目中的util，加入了更多了效果)
####引入
```
dependencies {
compile 'com.zyyoona7:popwindow:1.0.5'
}
```

####实现了popupWindow的几种方式，底部弹出，带半透明效果的底部弹出，顶部弹出，带半透明效果的顶部弹出(新增)，类似dialog的中间弹出，带半透明效果的中间弹出，相对控件上方弹出，相对控件的左下方弹出，右下方弹出，并加入相应的动画特效

#####已知问题：Activity在设置了<item name="android:windowIsTranslucent">true</item>后，4.2以下的版本设置dimAmount无效(原因不明)

显示在底部的popupWindow带半透明效果
```
   PopupWindowUtil popupWindow = new PopupWindowUtil(btn3);
   popupWindow.setContentView(R.layout.popup_window);
   popupWindow.showBottomWithAlpha();
```
显示在底部的popupwindow不带透明效果
```
   PopupWindowUtil popupWindow = new PopupWindowUtil(btn1);
   popupWindow.setContentView(R.layout.popup_window);
   Button button1 = (Button) popupWindow.findId(R.id.btn_onClick0);
   button1.setOnClickListener(v1 -> popupWindow.dismiss());
   popupWindow.showBottom();
```
具体效果的见demo
