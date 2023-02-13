package com.redpine.home.presentation.onboarding.view.setting

import android.graphics.RectF
import android.util.Log

class ProgressBarSettings {

    val listProgressBar = mutableListOf<UnitProgress>()
    var indicator = UnitProgress(StatusDottedProgressBar.FULL, RectF())

    private var width = 0
    private var height = 0
    private var startDrawVertical = 0

    fun createSettings(width: Int, height: Int, startDrawVertical: Int) {
        this.width = width
        this.height = height
        this.startDrawVertical = startDrawVertical
    }

    fun createProgressBar(
        size: Int,
        progress: Int? = null,
    ): MutableList<UnitProgress> {
        val dottedSize = (width / size).toFloat()
        val padding = dottedSize * DOTTED_PADDING
        listProgressBar.clear()
        for (i in 0..size) {
            val status = if (progress != null && i < progress) StatusDottedProgressBar.FULL
            else StatusDottedProgressBar.EMPTY
            listProgressBar
                .add(
                    UnitProgress(status,
                    createDottedRect(dottedSize, i, padding, height, startDrawVertical))
                )
        }
        return listProgressBar
    }

    private fun createDottedRect(
        dottedSize: Float,
        position: Int,
        padding: Float,
        height: Int,
        startDrawVertical: Int,
    ): RectF {
        val left = padding + dottedSize * position
        return RectF(left,
            startDrawVertical.toFloat(),
            left + dottedSize - padding - padding,
            height + startDrawVertical.toFloat())
    }

    fun setProgress(progress: Int) {
        try {
            if (listProgressBar[progress].status == StatusDottedProgressBar.EMPTY)
                for (i in 0..progress)
                    listProgressBar[i].status = StatusDottedProgressBar.FULL

            if (listProgressBar[progress].status == StatusDottedProgressBar.FULL) {
                for (i in progress..listProgressBar.size) {
                    if (listProgressBar[i].status == StatusDottedProgressBar.EMPTY) break
                    listProgressBar[i].status = StatusDottedProgressBar.EMPTY
                }
            }
        } catch (e: Exception) {
            Log.e("Kart", "${e.message}")
        }
    }

    fun toScrollIndicator(position: Int, positionOffSet: Float) {
        indicator.rect = listProgressBar[position].rect.toRectF()

        val progress = (listProgressBar[position].rect.right - indicator.rect.left) * positionOffSet

        indicator.rect.right = indicator.rect.left+progress
    }

    private fun RectF.toRectF() = RectF(this)

    companion object {
        const val DOTTED_PADDING = 0.05F
    }

}

