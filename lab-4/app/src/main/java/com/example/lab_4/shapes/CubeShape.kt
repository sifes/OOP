package com.example.lab_4.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.math.abs

class CubeShape(override val paint: Paint) : RectShape(paint), DrawableLine {
    override fun draw(canvas: Canvas) {
        if (rubbedTrace) setRubbedTraceDrawing() else setDrawConfig()

        val width = abs(endX - startX)
        val height = abs(endY - startY)

        val frontLeftX = startX - width
        val frontTopY = startY - height
        val frontRightX = startX + width
        val frontBottomY = startY + height

        val backLeftX = frontLeftX + (width / 2)
        val backTopY = frontTopY - height
        val backRightX = frontRightX + (width / 2)
        val backBottomY = frontBottomY - height

        canvas.drawRect(frontLeftX, frontTopY, frontRightX, frontBottomY, paint)
        canvas.drawRect(backLeftX, backTopY, backRightX, backBottomY, paint)

        drawLine(canvas, frontLeftX, frontBottomY, backLeftX, backBottomY, paint)
        drawLine(canvas, frontRightX, frontTopY, backRightX, backTopY, paint)
        drawLine(canvas, frontLeftX, frontTopY, backLeftX, backTopY, paint)
        drawLine(canvas, frontRightX, frontBottomY, backRightX, backBottomY, paint)
    }

    override fun setDrawConfig() {
        paint.apply {
            color = Color.BLACK
            pathEffect = null
            style = Paint.Style.STROKE
            strokeWidth = 5f
        }
    }

    override fun drawLine(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float, paint: Paint) {
        canvas.drawLine(startX, startY, endX, endY, paint)
    }
}
