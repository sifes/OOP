package com.example.lab_3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.lab_3.editors.EllipseEditor
import com.example.lab_3.editors.LineEditor
import com.example.lab_3.editors.PointEditor
import com.example.lab_3.editors.RectEditor
import com.example.lab_3.editors.ShapeObjectsEditor
import com.example.lab_3.shapes.Shape

private const val USER_STROKE_WIDTH = 6f

class CustomCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // Paint object to define the appearance of shapes
    private val paint = Paint().apply {
        color = Color.BLACK
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeWidth = USER_STROKE_WIDTH
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    private var currentX = 0f
    private var currentY = 0f

    // List of drawn shapes
    private val shapes = mutableListOf<Shape>()

    // Current shape editor, initialized to PointEditor
    private var currentShapeEditor: ShapeObjectsEditor = PointEditor(paint, shapes)

    enum class ShapeOption {
        POINT, LINE, RECTANGLE, ELLIPSE
    }

    fun setShapeEditor(option: ShapeOption) {
        currentShapeEditor = when (option) {
            ShapeOption.POINT -> PointEditor(paint, shapes)
            ShapeOption.LINE -> LineEditor(paint, shapes)
            ShapeOption.RECTANGLE -> RectEditor(paint, shapes)
            ShapeOption.ELLIPSE -> EllipseEditor(paint, shapes)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        currentX = event.x
        currentY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                handleTouchStart()
                performClick()
            }
            MotionEvent.ACTION_MOVE -> handleTouchMove()
            MotionEvent.ACTION_UP -> handleTouchUp()
        }

        return true
    }

    // Start drawing the shape
    private fun handleTouchStart() {
        currentShapeEditor.onTouchDown(currentX, currentY)
        invalidate() // Redraw the view
    }

    // Update the shape while the finger moves
    private fun handleTouchMove() {
        currentShapeEditor.onMouseMove(currentX, currentY)
        invalidate()
    }

    // Finalize the shape when the finger is lifted
    private fun handleTouchUp() {
        currentShapeEditor.onTouchUp()
        invalidate()
    }

    // Draw shapes on the canvas
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        shapes.forEach { shape ->
            shape.draw(canvas)
        }

        // Logging for debugging
        Log.d("CustomCanvas", "Number of shapes: ${shapes.size}")
    }

    // Handle view size changes
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.d("CustomCanvas", "Size changed: width = $w, height = $h")
    }

    // Implement performClick for accessibility
    override fun performClick(): Boolean {
        super.performClick()
        return true
    }
}
