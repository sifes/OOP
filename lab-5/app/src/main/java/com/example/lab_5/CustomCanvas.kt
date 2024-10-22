package com.example.lab_5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.lab_5.shapeHelper.ShapeData
import com.example.lab_5.shapeHelper.ShapeHelper
import com.example.lab_5.shapes.CubeShape
import com.example.lab_5.shapes.EllipseShape
import com.example.lab_5.shapes.LineCirclesShape
import com.example.lab_5.shapes.LineShape
import com.example.lab_5.shapes.PointShape
import com.example.lab_5.shapes.RectShape
import com.example.lab_5.shapes.Shape

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
    private var shapeEditor = MyEditor.getInstance(paint, shapes)

    // Define a callback interface for shape data changes
    interface OnShapeDataChangedListener {
        fun onShapeDataChanged(shapesData: List<ShapeData>)
    }

    // Listener to notify whenever shapes change
    var onShapeDataChangedListener: OnShapeDataChangedListener? = null

    enum class ShapeOption {
        POINT, LINE, RECT, ELLIPSE, LINE_CIRCLES, CUBE
    }

    private val shapeMap = mapOf(
        ShapeOption.POINT to { PointShape(paint) },
        ShapeOption.LINE to { LineShape(paint) },
        ShapeOption.RECT to { RectShape(paint) },
        ShapeOption.ELLIPSE to { EllipseShape(paint) },
        ShapeOption.LINE_CIRCLES to { LineCirclesShape(paint) },
        ShapeOption.CUBE to { CubeShape(paint) }
    )

    private val shapeHelper = ShapeHelper(context)

    fun getShapesData(): List<ShapeData> {
        return shapeHelper.getAllShapesData(shapes)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                handleTouchStart(event.x, event.y)
                performClick()
            }
            MotionEvent.ACTION_MOVE -> handleTouchMove(event.x, event.y)
            MotionEvent.ACTION_UP -> {
                handleTouchUp()
                onShapeDataChangedListener?.onShapeDataChanged(getShapesData())
            }
        }
        return true
    }

    private fun handleTouchStart(x: Float, y: Float) {
        shapeEditor.onTouchDown(x, y)
        invalidate()
    }

    private fun handleTouchMove(x: Float, y: Float) {
        shapeEditor.onMouseMove(x, y)
        invalidate()
    }

    private fun handleTouchUp() {
        shapeEditor.onTouchUp()
        invalidate()
    }

    fun setShapeEditor(selectedShape: ShapeOption) {
        val shape = shapeMap[selectedShape]?.invoke() ?: return
        shapeEditor.setCurrentShape(shape)
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
