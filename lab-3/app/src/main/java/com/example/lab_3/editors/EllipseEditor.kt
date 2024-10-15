package com.example.lab_3.editors

import android.graphics.Paint
import com.example.lab_3.CreateShape
import com.example.lab_3.shapes.EllipseShape
import com.example.lab_3.shapes.Shape

class EllipseEditor(paint: Paint, shapes: MutableList<Shape>) : ShapeObjectsEditor(paint, shapes) {
    private var currentShape: EllipseShape? = null

    override fun onTouchDown(x: Float, y: Float) {
        currentShape = EllipseShape(paint).apply {
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

