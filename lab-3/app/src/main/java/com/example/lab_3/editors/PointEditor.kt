package com.example.lab_3.editors

import android.graphics.Paint
import com.example.lab_3.CreateShape
import com.example.lab_3.shapes.PointShape
import com.example.lab_3.shapes.Shape

class PointEditor(paint: Paint, shapes: MutableList<Shape>) : ShapeObjectsEditor(paint, shapes) {
    private var currentShape: PointShape = PointShape(paint)

    override fun onTouchDown(x: Float, y: Float) {
        currentShape = PointShape(paint)
        currentShape.setStartCoords(x, y)
    }

    override fun onTouchUp() {
        CreateShape.addShape(currentShape, shapes)
        currentShape = PointShape(paint)
    }
}
