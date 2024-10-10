package com.example.lab_2.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint

class LineShape(override val paint: Paint) : Shape(paint) {
    private var rubbedTrace = true
    fun toggleRubbedTrace() {
        rubbedTrace = !rubbedTrace
    }

    override fun draw(canvas: Canvas) {
        setDrawConfig()
        canvas.drawLine(startX, startY, endX, endY,  paint)
    }

    override fun setDrawConfig() {
        paint.apply {
            style = if (rubbedTrace) Paint.Style.STROKE else Paint.Style.FILL
            color = if (rubbedTrace) Color.BLUE else Color.BLACK
            strokeWidth = 20f
        }
    }
}