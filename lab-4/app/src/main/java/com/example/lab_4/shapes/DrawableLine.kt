package com.example.lab_4.shapes

import android.graphics.Canvas
import android.graphics.Paint

interface DrawableLine {
  fun drawLine(canvas: Canvas, startX: Float, startY: Float, endX: Float, endY: Float, paint: Paint)
}