package com.example.lab_3.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

open class LineShape(override val paint: Paint) : Shape(paint) {
    override fun draw(canvas: Canvas) {
        if (rubbedTrace) setRubbedTraceDrawing() else setDrawConfig()
        canvas.drawLine(startX, startY, endX, endY,  paint)
    }

    override fun setDrawConfig() {
        paint.apply {
            style = Paint.Style.FILL
            color = Color.BLACK
            pathEffect = null
            strokeWidth = 5f
        }
    }
}