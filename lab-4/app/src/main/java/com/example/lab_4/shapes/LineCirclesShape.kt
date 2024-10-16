package com.example.lab_4.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class LineCirclesShape(override val paint: Paint) : LineShape(paint) {
    private val r = 20f

    override fun draw (canvas: Canvas) {
        super.draw(canvas)
        setDrawConfig()
        canvas.drawCircle(startX, startY, r, paint)
        canvas.drawCircle(endX, endY, r, paint)
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