package com.example.object2

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random
import android.graphics.Color
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow

class MainActivity : AppCompatActivity() {
  private lateinit var matrix: List<List<Double>>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val n = intent.getIntExtra("n", 12)
    val min = intent.getIntExtra("min", 4)
    val max = intent.getIntExtra("max", 5)

    matrix = generateMatrix(n, min, max)
    displayMatrix(matrix)
    copyMatrixToClipboard(matrix, this)

    val continueButton: Button = findViewById(R.id.sort_button)

    continueButton.setOnClickListener {
      launchObject3()
    }
  }

  override fun onNewIntent(intent: Intent) {
    super.onNewIntent(intent)
    setIntent(intent)
  }

  private fun generateMatrix(n: Int, min: Int, max: Int): List<List<Double>> {
    return List(n) {
      List(n) {
        Random.nextDouble(min.toDouble(), max.toDouble())
      }
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

  private fun copyMatrixToClipboard(matrix: List<List<Double>>, context: Context) {
    val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager

    val textToCopy = buildString {
      matrix.forEach { row ->
        append(row.joinToString("\t") { String.format("%.2f", it) })
        append("\n")
      }
    }

    val clip = ClipData.newPlainText("matrix", textToCopy.trim())
    clipboard.setPrimaryClip(clip)
  }

  private fun launchObject3() {
    val obj3Intent = packageManager.getLaunchIntentForPackage("com.example.object3")
    obj3Intent?.apply {
      addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
      startActivity(this)
    }
  }
}
