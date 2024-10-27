package com.example.lab_5.shapeHelpers

import android.content.Context
import com.example.lab_5.R
import com.example.lab_5.shapes.Shape

class ShapeHelper(context: Context) {
  private val shapeNameMap = mapOf(
    "PointShape" to context.getString(R.string.point),
    "LineShape" to context.getString(R.string.line),
    "RectShape" to context.getString(R.string.rect),
    "EllipseShape" to context.getString(R.string.ellipse),
    "LineCirclesShape" to context.getString(R.string.line_circles),
    "CubeShape" to context.getString(R.string.cube)
  )

  fun getAllShapesData(shapes: List<Shape>): List<ShapeData> {
    return shapes.map { shape ->
      val className = shape.javaClass.simpleName
      val localizedShapeName = shapeNameMap[className] ?: className
      val (startCoords, endCoords) = shape.getCoords()

      ShapeData(
        shapeName = localizedShapeName,
        startX = startCoords.first,
        startY = startCoords.second,
        endX = endCoords.first,
        endY = endCoords.second
      )
    }
  }
}
