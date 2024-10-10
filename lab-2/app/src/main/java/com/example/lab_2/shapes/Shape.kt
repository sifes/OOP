package com.example.lab_2.shapes

import android.graphics.Canvas
import android.graphics.Paint

abstract class Shape (open val paint : Paint) {
    protected var startX: Float = 0f
    protected var startY: Float = 0f

    protected var endX: Float = 0f
    protected var endY: Float = 0f


    fun setStartCoords (x1: Float, y1: Float) {
        startX = x1
        startY = y1

    }

    fun setEndCoords (x2: Float, y2: Float) {
        endX  = x2
        endY  = y2
    }


    abstract fun draw (canvas: Canvas)

    abstract fun setDrawConfig ()
}