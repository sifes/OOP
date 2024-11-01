package com.example.object3

import android.content.ClipboardManager
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.Gravity
import android.widget.Toast

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    processClipboardData()
  }

  override fun onNewIntent(intent: Intent) {
    super.onNewIntent(intent)
    setIntent(intent)
  }


  override fun onWindowFocusChanged (hasFocus: Boolean) {
    super.onWindowFocusChanged(hasFocus)
    if (hasFocus) {
      processClipboardData()
    }
  }

  private fun processClipboardData() {
    val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    val clipboardText = clipboard.primaryClip?.getItemAt(0)?.text?.toString()

    if (clipboardText == null) {
      displayError("Clipboard is empty or not accessible.")
      return
    }

    try {
      val rows = clipboardText.trim().split("\n")
      val matrix = rows.map { row ->
        row.split("\t").map { it.toDouble() }
      }


      val sortedMatrix = matrix.map { row -> row.sorted() }.sortedBy { it[0] }


      // Сортує рядки матриці за сумою елементів у рядку (від меншого до більшого).
      //      val sortedMatrix = matrix.sortedBy { it.sum() }

      // Сортує кожен рядок окремо в порядку спадання.
      //      val sortedMatrix = matrix.map { row -> row.sortedDescending() }

      // Сортує рядки матриці за мінімальним значенням у кожному рядку (від меншого до більшого).
      //      val sortedMatrix = matrix.sortedBy { row -> row.minOrNull() }

      displayMatrix(sortedMatrix)
    } catch (e: NumberFormatException) {
      displayError("Invalid matrix format in clipboard.")
    } catch (e: Exception) {
      displayError("An error occurred while processing clipboard data.")
    }
  }

  private fun displayMatrix(matrix: List<List<Double>>) {
    val tableLayout: TableLayout = findViewById(R.id.tableLayout)
    tableLayout.removeAllViews()

    for (row in matrix) {
      val tableRow = TableRow(this)
      tableRow.layoutParams = TableRow.LayoutParams(
        TableRow.LayoutParams.MATCH_PARENT,
        TableRow.LayoutParams.WRAP_CONTENT
      )

      for (value in row) {
        val cell = TextView(this)
        cell.text = String.format("%.2f", value)
        cell.setPadding(16, 16, 16, 16)
        cell.setTextColor(Color.BLACK)
        cell.gravity = Gravity.CENTER
        cell.setBackgroundColor(Color.WHITE)

        val cellParams = TableRow.LayoutParams(
          TableRow.LayoutParams.WRAP_CONTENT,
          TableRow.LayoutParams.WRAP_CONTENT
        )
        cellParams.setMargins(4, 4, 4, 4)
        cell.layoutParams = cellParams

        tableRow.addView(cell)
      }

      tableLayout.addView(tableRow)
    }
  }

  private fun displayError(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
  }

}
