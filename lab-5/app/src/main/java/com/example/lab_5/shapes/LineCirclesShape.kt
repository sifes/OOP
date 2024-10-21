package com.example.lab_5.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class LineCirclesShape(override val paint: Paint) : LineShape(paint) {
    override fun draw (canvas: Canvas) {
        super.draw(canvas)
        setDrawConfig()
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
}