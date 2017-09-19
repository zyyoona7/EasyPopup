package com.zyyoona7.lib

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.PopupWindow

/**
 * Created by zyyoona7 on 2017/9/19.
 *
 */
fun <T : EasyPopup> EasyPopup.init(block: EasyPopup.() -> Unit):T {
   return this.apply {
        block
    }.createPopup()
}

open class EasyPopup(val context: Context) {

    //PopupWindow对象
    val popupWindow: PopupWindow = PopupWindow()
    var contentView: View? = null
    var layoutId:Int = 0
    var width = 0
    var height = 0
    var focusAndOutsideEnable = false
    var focusable = true
    var outsideTouchable = true

    internal fun <T:EasyPopup> createPopup(): T {
        onPopupWindowCreated()
        if (contentView == null) {
            if (layoutId != 0) {
                contentView = LayoutInflater.from(context).inflate(layoutId, null)
            } else {
                throw IllegalArgumentException("The content view is null")
            }
        }
        popupWindow.contentView = contentView
        popupWindow.width = if (width == 0) ViewGroup.LayoutParams.WRAP_CONTENT else width
        popupWindow.height = if (height == 0) ViewGroup.LayoutParams.WRAP_CONTENT else height
        onPopupWindowViewCreated(contentView)

        if (!focusAndOutsideEnable) {
            popupWindow.isFocusable = true
            popupWindow.isOutsideTouchable = false
            popupWindow.setBackgroundDrawable(null)

            popupWindow.contentView.isFocusable = true
            popupWindow.contentView.isFocusableInTouchMode = true
            popupWindow.contentView.setOnKeyListener { v, keyCode, event ->
                return@setOnKeyListener if (keyCode == KeyEvent.KEYCODE_BACK) {
                    popupWindow.dismiss()
                    true
                } else {
                    false
                }
            }

            popupWindow.setTouchInterceptor { v, event ->
                return@setTouchInterceptor (event.action == MotionEvent.ACTION_DOWN &&
                        ((event.x < 0 || event.x > width) || (event.y < 0 || event.y > height)) || event.action == MotionEvent.ACTION_OUTSIDE)
            }
        } else {
            popupWindow.isFocusable = focusable
            popupWindow.isOutsideTouchable = outsideTouchable
            popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        return this as T
    }

    /****自定义生命周期方法****/

    /**
     * PopupWindow对象创建完成
     */
    open fun onPopupWindowCreated() {}

    open fun onPopupWindowViewCreated(contentView: View?) {}

    open fun onPopupWindowDismiss() {}
}