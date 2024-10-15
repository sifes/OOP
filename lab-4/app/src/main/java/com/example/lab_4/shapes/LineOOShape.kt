package com.example.lab_4.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint

class LineOOShape(override val paint: Paint) : LineShape(paint) {
    private val r = 10f

    override fun draw (canvas: Canvas) {
        super.draw(canvas)
        setDrawConfig()
        canvas.drawCircle(startX, startY, r, paint)
        canvas.drawCircle(endX, endY, r, paint)
    }

    override fun setDrawConfig () {
        paint.apply {
            color = if (rubbedTrace) Color.BLACK else Color.WHITE
            pathEffect = if (rubbedTrace) DashPathEffect(floatArrayOf(10f, 10f), 0f) else null
            style = if (rubbedTrace) Paint.Style.STROKE else Paint.Style.FILL
            strokeWidth = 5f
        }
    }
}