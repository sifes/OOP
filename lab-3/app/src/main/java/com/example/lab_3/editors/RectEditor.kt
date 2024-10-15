package com.example.lab_3.editors

import android.graphics.Paint
import com.example.lab_3.CreateShape
import com.example.lab_3.shapes.RectShape
import com.example.lab_3.shapes.Shape

class RectEditor(paint: Paint, shapes: MutableList<Shape>) : ShapeObjectsEditor(paint, shapes) {
    private var currentShape: RectShape? = null

    override fun onTouchDown(x: Float, y: Float) {
        currentShape = RectShape(paint).apply {
            setStartCoords(x, y)
        }

    }

    override fun onMouseMove(x: Float, y: Float) {
        currentShape?.let {
            if (shapes.contains(it)) shapes.remove(it)
            it.setEndCoords(x, y)
            CreateShape.addShape(it, shapes)
        }
    }

    override fun onTouchUp() {
        currentShape?.toggleRubbedTrace()
        currentShape = null
    }
}
