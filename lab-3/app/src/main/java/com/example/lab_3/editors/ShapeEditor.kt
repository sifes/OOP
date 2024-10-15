package com.example.lab_3.editors
import android.graphics.Paint
import com.example.lab_3.shapes.Shape

abstract class ShapeObjectsEditor (val paint: Paint, val shapes: MutableList<Shape>) : Editor() {
        override fun onTouchUp () {}
        override fun onTouchDown (x: Float, y: Float) {}
        override fun onMouseMove (x: Float, y: Float) {}
}