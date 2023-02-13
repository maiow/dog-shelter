package com.redpine.home.presentation.onboarding.view.mediator

import androidx.viewpager2.widget.ViewPager2
import com.redpine.home.presentation.onboarding.view.DottedProgressBar
import java.lang.ref.WeakReference

class DottedProgressOnPageProgressChangeCallback(dottedProgressBar: DottedProgressBar): ViewPager2.OnPageChangeCallback(){
    private var progressRef: WeakReference<DottedProgressBar>
    private var previousScrollState = 0
    private var scrollState = 0

    private fun reset() {
        scrollState = ViewPager2.SCROLL_STATE_IDLE
        previousScrollState = scrollState
    }

    init {
        progressRef = WeakReference(dottedProgressBar)
        reset()
    }

    override fun onPageScrollStateChanged(state: Int) {
        previousScrollState = scrollState
        scrollState = state
    }

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        progressRef.get()?.setProgress(position+1)
    }
}