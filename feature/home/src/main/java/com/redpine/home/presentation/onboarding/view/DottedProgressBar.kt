package com.redpine.home.presentation.onboarding.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.redpine.home.presentation.onboarding.view.setting.ProgressBarSettings
import com.redpine.home.presentation.onboarding.view.setting.StatusDottedProgressBar
import com.redpine.home.R

class DottedProgressBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {

    private val settings = ProgressBarSettings()
    private var progressSize = DEFAULT_PROGRESS_SIZE
    private var progressEmptyColor = DEFAULT_COLOR_EMPTY
    private var progressFullColor = DEFAULT_COLOR_FULL
    private val progressHeight = DEFAULT_HEIGHT
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private var progress = progressSize / 2

    init {
        attrs?.let { setAttribute(it) }
    }

    @SuppressLint("Recycle")
    private fun setAttribute(attrs: AttributeSet) {
        val typArray = context.obtainStyledAttributes(attrs, R.styleable.DottedProgressBar)
        progressEmptyColor = typArray.getColor(
            R.styleable.DottedProgressBar_progress_empty_Color,
            DEFAULT_COLOR_EMPTY
        )
        progressFullColor = typArray.getColor(
            R.styleable.DottedProgressBar_progress_full_Color,
            DEFAULT_COLOR_FULL
        )
        progressSize = typArray.getInt(
            R.styleable.DottedProgressBar_progress_size,
            DEFAULT_PROGRESS_SIZE
        )
        progress = typArray.getInt(R.styleable.DottedProgressBar_progress, progressSize / 2)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        settings.createSettings(w, progressHeight, h / 2 + progressHeight / 2)
        settings.createProgressBar(progressSize, progress)
            .toMutableList()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        settings.listProgressBar.forEach {
            paint.color = if (it.status == StatusDottedProgressBar.EMPTY) progressEmptyColor
            else progressFullColor
            canvas?.drawRoundRect(it.rect, 5f, 5f, paint)
        }
        paint.color = progressFullColor
        canvas?.drawRoundRect(settings.indicator.rect, 5f, 5f, paint)
    }

    fun setProgress(progress: Int) {
        this.progress = progress
        settings.setProgress(progress)
        invalidate()
    }

    fun getProgress() = progress

    fun setProgressSize(size: Int) {
        progressSize = size
        settings.createProgressBar(size)
    }

    companion object {
        const val DEFAULT_PROGRESS_SIZE = 15
        const val DEFAULT_COLOR_FULL = Color.CYAN
        const val DEFAULT_COLOR_EMPTY = Color.DKGRAY
        const val DEFAULT_HEIGHT = 10
    }
}