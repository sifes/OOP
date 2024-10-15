package com.example.lab_3.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.RectF
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


class RectShape(override val paint: Paint) : Shape(paint) {
    private var rubbedTrace = true
    fun toggleRubbedTrace() {
        rubbedTrace = !rubbedTrace
    }

    override fun draw (canvas: Canvas) {
        setDrawConfig()

        val width = abs(endX - startX)
        val height = abs(endY - startY)

        val left = startX - width
        val top = startY - height
        val right = startX + width
        val bottom = startY + height

        canvas.drawRect(left, top, right, bottom, paint)

        if (!rubbedTrace) {
            setBorderConfig()
            canvas.drawRect(left, top, right, bottom, paint)
        }
    }

    override fun setDrawConfig () {
        paint.apply {
            color = if (rubbedTrace) Color.BLACK else Color.WHITE
            pathEffect = if (rubbedTrace) DashPathEffect(floatArrayOf(10f, 10f), 0f) else null
            style = if (rubbedTrace) Paint.Style.STROKE else Paint.Style.FILL
            strokeWidth = 5f
        }
    }

    private fun setBorderConfig() {
        paint.apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
        }
    }
}