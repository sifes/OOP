package com.example.lab_5.shapeHelper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShapeViewModel : ViewModel() {
  private val _shapesData = MutableLiveData<List<ShapeData>>()
  val shapesData: LiveData<List<ShapeData>> get() = _shapesData

  fun updateShapesData(newShapes: List<ShapeData>) {
    _shapesData.value = newShapes
  }
}

