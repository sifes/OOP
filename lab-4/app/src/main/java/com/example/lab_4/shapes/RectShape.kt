package com.example.lab_4.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.math.abs


open class RectShape(override val paint: Paint) : Shape(paint) {
    override fun draw (canvas: Canvas) {
        if (rubbedTrace) setRubbedTraceDrawing() else setDrawConfig()

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
            color = Color.WHITE
            pathEffect = null
            style = Paint.Style.FILL
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