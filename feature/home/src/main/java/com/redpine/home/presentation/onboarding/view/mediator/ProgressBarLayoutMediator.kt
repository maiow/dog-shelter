package com.redpine.home.presentation.onboarding.view.mediator

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.redpine.home.presentation.onboarding.view.DottedProgressBar

class ProgressBarLayoutMediator(
    private var progressBar: DottedProgressBar?,
    private var viewPager: ViewPager2?,
) {
    private var onPageChangeCallback: DottedProgressOnPageProgressChangeCallback? = null

    private var adapter: RecyclerView.Adapter<*>? = null

    private var attached = false

    fun attach() {
        check(!attached) { throw IllegalStateException("ProgressBar is already attached") }
        adapter = viewPager!!.adapter
        checkNotNull(adapter) {
            throw IllegalStateException("ProgressBar attached before ViewPager2 has an " + "adapter")
        }
        attached = true

        onPageChangeCallback = progressBar?.let { DottedProgressOnPageProgressChangeCallback(it) }
        viewPager!!.registerOnPageChangeCallback(onPageChangeCallback!!)

        progressBar?.setProgress(viewPager!!.currentItem)

        if (adapter != null)
            progressBar?.setProgressSize(viewPager!!.adapter!!.itemCount)

        progressBar?.let { viewPager!!.setCurrentItem(it.getProgress(), true) }
    }
}