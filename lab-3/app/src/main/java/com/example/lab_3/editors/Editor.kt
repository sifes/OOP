package com.example.lab_3.editors


abstract class Editor {
    abstract fun onMouseMove (x: Float, y: Float)
    abstract fun onTouchUp ()
    abstract fun onTouchDown (x: Float, y: Float)
}