package com.henryford.ui.util

import android.app.Activity
import android.content.Context
import android.graphics.Rect

object ScreenUtil {
    //dp转化成px
    fun dp2Px(context: Context, dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    fun px2Dp(context: Context, pixelValue: Int): Int {
        val density = context.resources.displayMetrics.density
        return (pixelValue / density + 0.5f).toInt()
    }

    fun getScreenWidth(context: Context): Int {
        val dm = context.resources.displayMetrics
        return dm.widthPixels
    }

    fun getScreenHeight(context: Context): Int {
        val dm = context.resources.displayMetrics
        return dm.heightPixels
    }

    fun getScreenRealHeight(activity: Activity): Int {
        val rect = Rect()
        activity.window.decorView.getWindowVisibleDisplayFrame(rect)
        val screenHeight = getScreenHeight(activity)
        return if (rect.height() > screenHeight) rect.height() else screenHeight
    }
}