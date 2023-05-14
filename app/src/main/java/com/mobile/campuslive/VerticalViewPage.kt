package com.mobile.campuslive

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs

class VerticalViewPage : ViewPager {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setPageTransformer(true, VerticalPageTransformer())
        overScrollMode = OVER_SCROLL_NEVER
    }

    private inner class VerticalPageTransformer : PageTransformer {
        override fun transformPage(page: View, position: Float) {
            // here will be check position
            if (position < -1) {
                //-infinity to -1
                page.alpha = 0f
            } else if (position <= 0) {
                //-1 to 0
                page.alpha = 1f
                page.translationX = page.width * -position
                val yPosition = position * page.height
                page.translationY = yPosition
                page.scaleX = 1f
                page.scaleY = 1f
            } else if (position <= 1) {
                //0 to 1
                page.translationX = page.width * -position
                val scale = 0.75f + (1 - 0.75f) * (1 - abs(position))
                page.scaleX = scale
                page.scaleY = scale
            } else {
                //1 to + infinity
                page.alpha = 0f
            }
        }
    }

    private fun swapXYCordinates(event: MotionEvent): MotionEvent {
        val width = width.toFloat()
        val height = height.toFloat()
        val newX = event.y / height * width
        val newY = event.x / width * height
        event.setLocation(newX, newY)
        return event
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val intercepted = super.onInterceptTouchEvent(swapXYCordinates(ev))
        swapXYCordinates(ev)
        return intercepted
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return super.onTouchEvent(swapXYCordinates(ev))
    }
}