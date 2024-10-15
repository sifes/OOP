package com.example.lab_3

import com.example.lab_3.shapes.Shape

class CreateShape {
    companion object {
        private const val MAX_SIZE: Int = 114
        fun addShape(shape: Shape, shapes: MutableList<Shape>) {
            if (shapes.lastIndex == MAX_SIZE - 1) {
                shapes.removeAt(shapes.lastIndex)
            }
            shapes.add(shape)
        }
    }
}