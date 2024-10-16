package com.example.lab_4

import android.graphics.Paint
import com.example.lab_4.shapes.Shape

class MyEditor(private val paint: Paint, private val shapes: MutableList<Shape>) {
    private var currentShape: Shape = Shape(paint)
    private val shapesSize: Int = 114

    fun onTouchDown(x: Float, y: Float) {
        setCurrentShape(currentShape)
        currentShape.setStartCoords(x, y)
    }

    fun onTouchUp() {
        currentShape.let {
            if (shapes.contains(it)) shapes.remove(it)
            it.toggleRubbedTrace()
            addShapeToEditor(it, shapes)
        }
    }

    fun onMouseMove(x: Float, y: Float) {
        currentShape.let {
            if (shapes.contains(it)) shapes.remove(it)
            it.setEndCoords(x, y)
            addShapeToEditor(it, shapes)
        }
    }

    fun setCurrentShape (shape: Shape) {
        currentShape = shape.javaClass.getDeclaredConstructor(Paint::class.java).newInstance(paint)
    }

    private fun addShapeToEditor(shape: Shape, shapes: MutableList<Shape>) {
        if (shapes.lastIndex == shapesSize - 1) {
            shapes.removeAt(shapes.lastIndex)
        }
        shapes.add(shape)
    }
}
