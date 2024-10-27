package com.example.lab_5.table

import android.content.Context
import android.util.AttributeSet
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.lab_5.R
import com.example.lab_5.shapeHelpers.ShapeData

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

      val shapeNameTextView = createTextView(context, shape.shapeName, 1f)
      val x1TextView = createTextView(context, shape.startX.toString(), 0.25f)
      val y1TextView = createTextView(context, shape.startY.toString(), 0.25f)
      val x2TextView = createTextView(context, shape.endX.toString(), 0.25f)
      val y2TextView = createTextView(context, shape.endY.toString(), 0.25f)

      tableRow.addView(shapeNameTextView)
      tableRow.addView(x1TextView)
      tableRow.addView(y1TextView)
      tableRow.addView(x2TextView)
      tableRow.addView(y2TextView)

      tableLayout.addView(tableRow)
    }
  }

 private  fun createTextView(context: Context, text: String, weight: Float): TextView {
    return TextView(context).apply {
      this.text = text
      setPadding(12, 8, 12, 8)
      layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, weight)
    }
  }
}
