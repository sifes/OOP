package com.example.lab6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
  private lateinit var pointsNumber: EditText
  private lateinit var min: EditText
  private lateinit var max: EditText
  private lateinit var performButton: Button

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    pointsNumber = findViewById(R.id.number_text)
    min = findViewById(R.id.xMin_text)
    max = findViewById(R.id.xMax_text)
    performButton = findViewById(R.id.perform_button)

    performButton.setOnClickListener {
      if (validateInput()) {
        launchObject2Intent()
      }
    }
  }

  private fun validateInput(): Boolean {
    val n = pointsNumber.text.toString().toIntOrNull()
    val minVal = min.text.toString().toIntOrNull()
    val maxVal = max.text.toString().toIntOrNull()

    return when {
      n == null || n <= 0 -> {
        Toast.makeText(this, "Please enter a valid positive number for matrix size", Toast.LENGTH_SHORT).show()
        false
      }
      minVal == null -> {
        Toast.makeText(this, "Please enter a valid minimum value", Toast.LENGTH_SHORT).show()
        false
      }
      maxVal == null -> {
        Toast.makeText(this, "Please enter a valid maximum value", Toast.LENGTH_SHORT).show()
        false
      }
      maxVal <= minVal -> {
        Toast.makeText(this, "Maximum value must be greater than minimum value", Toast.LENGTH_SHORT).show()
        false
      }
      else -> true
    }
  }

  private fun launchObject2Intent() {
    val object2Intent: Intent? = packageManager.getLaunchIntentForPackage("com.example.object2")

    try {
      object2Intent?.apply {
        addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        putExtra("n", pointsNumber.text.toString().toIntOrNull() ?: 0)
        putExtra("min", min.text.toString().toIntOrNull() ?: 0)
        putExtra("max", max.text.toString().toIntOrNull() ?: 0)

        startActivity(this)
      }
    } catch (e: Exception) {
      Toast.makeText(this, "Error launching App 2: ${e.message}",
        Toast.LENGTH_SHORT).show()
    }
  }
}