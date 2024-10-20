package com.example.lab_3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.lab_3.editors.EllipseEditor
import com.example.lab_3.editors.LineEditor
import com.example.lab_3.editors.PointEditor
import com.example.lab_3.editors.RectEditor
import com.example.lab_3.editors.ShapeObjectsEditor
import com.example.lab_3.shapes.Shape

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

    private val shapes = mutableListOf<Shape>()

    private var currentShapeEditor: ShapeObjectsEditor = PointEditor(paint, shapes)

    enum class ShapeOption {
        POINT, LINE, RECT, ELLIPSE
    }

    private val shapeEditorMap: Map<ShapeOption, () -> ShapeObjectsEditor> = mapOf(
        ShapeOption.POINT to { PointEditor(paint, shapes) },
        ShapeOption.LINE to { LineEditor(paint, shapes) },
        ShapeOption.RECT to { RectEditor(paint, shapes) },
        ShapeOption.ELLIPSE to { EllipseEditor(paint, shapes) }
    )

    fun setShapeEditor(option: ShapeOption) {
        currentShapeEditor = shapeEditorMap[option]?.invoke() ?: PointEditor(paint, shapes)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                handleTouchStart(event.x, event.y)
                performClick()
            }
            MotionEvent.ACTION_MOVE -> handleTouchMove(event.x, event.y)
            MotionEvent.ACTION_UP -> handleTouchUp()
        }

        return true
    }

    private fun handleTouchStart(x: Float, y: Float) {
        currentShapeEditor.onTouchDown(x, y)
        invalidate()
    }

    private fun handleTouchMove(x: Float, y: Float) {
        currentShapeEditor.onMouseMove(x, y)
        invalidate()
    }

    private fun handleTouchUp() {
        currentShapeEditor.onTouchUp()
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        shapes.forEach { shape ->
            shape.draw(canvas)
        }
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }
}

