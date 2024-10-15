package com.example.lab_3.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint

class EllipseShape(override val paint: Paint) : Shape(paint) {
    override fun draw(canvas: Canvas) {
        if (rubbedTrace) setRubbedTraceDrawing() else setDrawConfig()
        canvas.drawOval(startX, startY, endX, endY, paint)

        if (!rubbedTrace) {
            setBorderConfig()
            canvas.drawOval(startX, startY, endX, endY, paint)
        }
    }

    override fun setDrawConfig() {
        paint.apply {
            color = if (rubbedTrace) Color.BLACK else Color.TRANSPARENT
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