package com.example.lab_4.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class LineCirclesShape(override val paint: Paint) : LineShape(paint), DrawableLine {
    override fun draw (canvas: Canvas) {
        setDrawConfig()
        drawLine(canvas, startX, startY, endX, endY, paint)
        canvas.drawCircle(startX, startY, 20f, paint)
        canvas.drawCircle(endX, endY, 20f, paint)
    }

    override fun setDrawConfig () {
        paint.apply {
            color = Color.BLACK
            pathEffect = null
            style = Paint.Style.FILL
            strokeWidth = 5f
        }
    }

    override fun drawLine(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float, paint: Paint) {
        canvas.drawLine(startX, startY, endX, endY, paint)
    }
}
