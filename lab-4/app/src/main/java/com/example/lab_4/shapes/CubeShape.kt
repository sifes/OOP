package com.example.lab_4.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.math.abs

class CubeShape(override val paint: Paint) : RectShape(paint) {
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

        canvas.drawLine(frontLeftX, frontBottomY, backLeftX, backBottomY, paint)
        canvas.drawLine(frontRightX, frontTopY, backRightX, backTopY, paint)
        canvas.drawLine(frontLeftX, frontTopY, backLeftX, backTopY, paint)
        canvas.drawLine(frontRightX, frontBottomY, backRightX, backBottomY, paint)
    }

    override fun setDrawConfig() {
        paint.apply {
            color = Color.BLACK
            pathEffect = null
            style = Paint.Style.STROKE
            strokeWidth = 5f
        }
    }
}
