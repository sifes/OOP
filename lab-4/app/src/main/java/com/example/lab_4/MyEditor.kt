package com.example.lab_4

import android.graphics.Paint
import com.example.lab_4.shapes.Shape

class MyEditor (private val paint: Paint, private val shapes: MutableList<Shape>) {
    private var currentShape = Shape(paint)
    private val shapesLimit: Int = 123

    fun onTouchDown (x: Float, y: Float) {
        defineCurrentShape(currentShape)
        currentShape.setStartCoords(x, y)
    }

    fun onTouchUp () {
        currentShape.let {
            if (shapes.contains(it)) shapes.remove(it)
            addShapeToEditor(it, shapes)
        }
    }

    fun handleMouseMovement (x: Float, y: Float) {
        currentShape.let {
            if (shapes.contains(it)) shapes.remove(it)
            it.setEndCoords(x, y)
            addShapeToEditor(it, shapes)
        }
    }

    fun defineCurrentShape (shape: Shape) {
        currentShape = shape.javaClass.getDeclaredConstructor(Paint::class.java).newInstance(paint)
    }

    private fun addShapeToEditor (shape: Shape, shapes: MutableList<Shape>) {
        if (shapes.lastIndex == shapesLimit - 1) {
            shapes.removeAt(shapes.lastIndex)
        }
        shapes.add(shape)
    }
}