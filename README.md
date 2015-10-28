# PopupWindowUtil
对popupWindow进行封装,用起来so easy
dependencies {
compile 'com.zyyoona7:popwindow:1.0.1'
}

实现了popupWindow的几种方式，底部弹出，带半透明效果的底部弹出，顶部弹出，类似dialog的中间弹出，带半透明效果的中间弹出
，相对控件上方弹出，相对控件的左下方弹出，右下方弹出，并加入相应的动画特效

显示在底部的popupWindow带半透明效果
   PopupWindowUtil popupWindow = new PopupWindowUtil(btn3);
   popupWindow.setContentView(R.layout.popup_window);
   popupWindow.showBottomWithAlpha(this);
   popupWindow.setOnDismissListener(() -> popupWindow.dismiss(this));
半透明效果需设置onDismissListener，否则半透明变不回来

具体效果的见demo
