package com.example.lab_5.shapes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import kotlin.math.roundToInt

open class Shape (open val paint : Paint) {
    protected var startX: Float = 0f
    protected var startY: Float = 0f

    protected var endX: Float = 0f
    protected var endY: Float = 0f

    protected var rubbedTrace = true

    fun toggleRubbedTrace() {
        rubbedTrace = !rubbedTrace
    }

    fun setStartCoords (x1: Float, y1: Float) {
        startX = x1
        startY = y1

    }

    fun setEndCoords (x2: Float, y2: Float) {
        endX  = x2
        endY  = y2
    }

    open fun draw (canvas: Canvas) {}

    open fun setDrawConfig () {}

    fun setRubbedTraceDrawing () {
        paint.apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            pathEffect = DashPathEffect(floatArrayOf(10f, 10f), 0f)
            strokeWidth = 5f
        }
    }

    fun getCoords(): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        return Pair(Pair(startX.roundToInt(), startY.roundToInt()), Pair(endX.roundToInt(), endY.roundToInt()))
    }
}