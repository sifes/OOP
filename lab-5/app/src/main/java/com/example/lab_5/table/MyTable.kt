package com.example.lab_5.table

import android.content.Context
import android.util.AttributeSet
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.lab_5.R
import com.example.lab_5.shapeHelper.ShapeData

class MyTable @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

  private val tableLayout: TableLayout

  init {
    inflate(context, R.layout.table_shapes, this)

    tableLayout = findViewById(R.id.tableLayout)
      ?: throw NullPointerException("TableLayout not found in MyTable layout")
  }

  fun updateShapesData(shapesData: List<ShapeData>) {
    val childCount = tableLayout.childCount
    if (childCount > 1) {
      tableLayout.removeViews(1, childCount - 1)
    }
    setShapesData(shapesData)
  }

  private fun setShapesData(shapesData: List<ShapeData>) {
    for (shape in shapesData) {
      val tableRow = TableRow(context).apply {
        background = context.getDrawable(R.drawable.table_row_border)
      }

      val shapeNameTextView = TextView(context).apply {
        text = shape.shapeName
        setPadding(12, 8, 12, 8)
        layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f) // Weight 1 for shapeName
      }

      val x1TextView = TextView(context).apply {
        text = shape.startX.toString()
        setPadding(12, 8, 12, 8)
        layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.25f) // Weight 0.25 for x1
      }

      val y1TextView = TextView(context).apply {
        text = shape.startY.toString()
        setPadding(12, 8, 12, 8)
        layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.25f) // Weight 0.25 for y1
      }

      val x2TextView = TextView(context).apply {
        text = shape.endX.toString()
        setPadding(12, 8, 12, 8)
        layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.25f) // Weight 0.25 for x2
      }

      val y2TextView = TextView(context).apply {
        text = shape.endY.toString()
        setPadding(12, 8, 12, 8)
        layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 0.25f) // Weight 0.25 for y2
      }

      tableRow.addView(shapeNameTextView)
      tableRow.addView(x1TextView)
      tableRow.addView(y1TextView)
      tableRow.addView(x2TextView)
      tableRow.addView(y2TextView)

      tableLayout.addView(tableRow)
    }
  }

}
