package com.example.lab_5.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class PointShape (override val paint: Paint) : Shape(paint) {
    override fun draw (canvas: Canvas) {
        setDrawConfig()
        canvas.drawPoint(startX, startY, paint)
    }

    override fun setDrawConfig () {
        paint.apply { color = Color.BLACK; strokeWidth = 20f }
    }
}