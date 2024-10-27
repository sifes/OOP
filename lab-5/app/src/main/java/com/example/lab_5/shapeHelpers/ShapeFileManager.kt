package com.example.lab_5.shapeHelpers

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.BufferedReader
import java.io.InputStreamReader

class ShapeFileManager(private val context: Context) {
  private val fileName = "shapes.txt"

  fun saveShapeToFile(shapeData: ShapeData) {
    val file = File(context.filesDir, fileName)

    val line = "${shapeData.shapeName}\t${shapeData.startX}\t${shapeData.startY}\t${shapeData.endX}\t${shapeData.endY}\n"

    try {
      val fos = FileOutputStream(file, true)
      val osw = OutputStreamWriter(fos)
      osw.append(line)
      osw.close()
      fos.close()
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  fun saveShapesToFile(shapeList: List<ShapeData>) {
    val file = File(context.filesDir, fileName)

    try {
      val fos = FileOutputStream(file)
      val osw = OutputStreamWriter(fos)
      shapeList.forEach { shape ->
        val line = "${shape.shapeName}\t${shape.startX}\t${shape.startY}\t${shape.endX}\t${shape.endY}\n"
        osw.append(line)
      }
      osw.close()
      fos.close()
    } catch (e: Exception) {
      e.printStackTrace()
    }
  }

  fun loadShapesFromFile(): List<ShapeData> {
    val shapeList = mutableListOf<ShapeData>()
    val file = File(context.filesDir, fileName)

    if (!file.exists()) {
      return shapeList
    }

    try {
      val fis = file.inputStream()
      val isr = InputStreamReader(fis)
      val reader = BufferedReader(isr)

      reader.forEachLine { line ->
        val data = line.split("\t")
        if (data.size == 5) {
          val shapeName = data[0]
          val startX = data[1].toIntOrNull() ?: 0
          val startY = data[2].toIntOrNull() ?: 0
          val endX = data[3].toIntOrNull() ?: 0
          val endY = data[4].toIntOrNull() ?: 0

          val shapeData = ShapeData(shapeName, startX, startY, endX, endY)
          shapeList.add(shapeData)
        }
      }
      reader.close()
      isr.close()
      fis.close()
    } catch (e: Exception) {
      e.printStackTrace()
    }

    return shapeList
  }

  fun clearFile() {
    val file = File(context.filesDir, fileName)

    if (file.exists()) {
      file.delete()
    }
  }
}
