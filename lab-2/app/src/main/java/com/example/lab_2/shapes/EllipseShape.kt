package com.example.lab_2.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.math.abs

class EllipseShape(override val paint: Paint) : Shape(paint) {
    private var rubbedTrace = true

    fun toggleRubbedTrace() {
        rubbedTrace = !rubbedTrace
    }

    override fun draw(canvas: Canvas) {
        val radiusX = abs(endX - startX)
        val radiusY = abs(endY - startY)

        setDrawConfig()

        canvas.drawOval(
            startX - radiusX,
            startY - radiusY,
            startX + radiusX,
            startY + radiusY,
            paint
        )

        if (!rubbedTrace) {
            setBorderConfig()
            canvas.drawOval(
                startX - radiusX,
                startY - radiusY,
                startX + radiusX,
                startY + radiusY,
                paint
            )
        }
    }

    override fun setDrawConfig() {
        if (rubbedTrace) {
            paint.apply {
                color = Color.BLUE
                style = Paint.Style.STROKE
                strokeWidth = 20f
            }
        } else {
            paint.apply {
                color = Color.rgb(0, 255, 0)
                style = Paint.Style.FILL
                strokeWidth = 0f
            }
        }
    }

    private fun setBorderConfig() {
        paint.apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 20f
        }
    }
}
