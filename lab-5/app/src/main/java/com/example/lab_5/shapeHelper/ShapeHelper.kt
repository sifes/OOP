package com.example.lab_5.shapeHelper

import com.example.lab_5.shapes.Shape

class ShapeHelper {
  fun getAllShapesData(shapes: List<Shape>): List<ShapeData> {
    return shapes.map { shape ->
      val className = shape.javaClass.simpleName
      val (startCoords, endCoords) = shape.getCoords()
      ShapeData(
        shapeName = className,
        startX = startCoords.first,
        startY = startCoords.second,
        endX = endCoords.first,
        endY = endCoords.second
      )
    }
  }
}
