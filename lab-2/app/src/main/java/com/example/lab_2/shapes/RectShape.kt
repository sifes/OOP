package com.example.lab_2.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.RectF


class RectShape(override val paint: Paint) : Shape(paint) {
    private var rubbedTrace = true
    fun toggleRubbedTrace() {
        rubbedTrace = !rubbedTrace
    }

    override fun draw (canvas: Canvas) {
        setDrawConfig()
        canvas.drawRect(startX, startY, endX, endY, paint)
    }

    override fun setDrawConfig () {
        paint.apply {
            color = if (rubbedTrace) Color.BLUE else Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 20f
        }
    }
}