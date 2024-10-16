package com.example.lab_4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.lab_4.shapes.CubeShape
import com.example.lab_4.shapes.EllipseShape
import com.example.lab_4.shapes.LineCirclesShape
import com.example.lab_4.shapes.LineShape
import com.example.lab_4.shapes.PointShape
import com.example.lab_4.shapes.RectShape
import com.example.lab_4.shapes.Shape

class CustomCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint().apply {
        color = Color.BLACK
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeWidth = 6f
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    private var currentX = 0f
    private var currentY = 0f

    private val shapes = mutableListOf<Shape>()

    private var shapeEditor = MyEditor(paint, shapes)

    enum class ShapeOption {
        POINT, LINE, RECT, ELLIPSE, LINE_CIRCLES, CUBE
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        currentX = event.x
        currentY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                handleTouchStart()
                performClick()
            }
            MotionEvent.ACTION_MOVE -> onMouseMove()
            MotionEvent.ACTION_UP -> handleTouchUp()
        }
        return true
    }

    private fun handleTouchStart() {
        invalidate()
        shapeEditor.onTouchDown(currentX, currentY)
    }

    private fun onMouseMove() {
        invalidate()
        shapeEditor.onMouseMove(currentX, currentY)
    }

    private fun handleTouchUp() {
        invalidate()
        shapeEditor.onTouchUp()
    }

    fun setShapeInEditor(selectedShape: ShapeOption) {
        val shape = when (selectedShape) {
            ShapeOption.POINT -> PointShape(paint)
            ShapeOption.LINE -> LineShape(paint)
            ShapeOption.RECT -> RectShape(paint)
            ShapeOption.ELLIPSE -> EllipseShape(paint)
            ShapeOption.LINE_CIRCLES -> LineCirclesShape(paint)
            ShapeOption.CUBE -> CubeShape(paint)
        }
        shapeEditor.setCurrentShape(shape)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        shapes.forEach { shape ->
            shape.draw(canvas)
        }
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }
}
