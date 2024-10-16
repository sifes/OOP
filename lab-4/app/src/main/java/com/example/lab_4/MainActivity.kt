package com.example.lab_4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import android.widget.Toast

class MainActivity : AppCompatActivity() {
  private lateinit var customCanvas: CustomCanvas
  private var currentShape: CustomCanvas.ShapeOption = CustomCanvas.ShapeOption.POINT
  private lateinit var mainMenu: Menu
  private var menuItemMap: MutableMap<Int, CustomCanvas.ShapeOption> = mutableMapOf()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val toolbar: Toolbar = findViewById(R.id.toolbar)
    setSupportActionBar(toolbar)

    customCanvas = findViewById(R.id.customCanvas)
    customCanvas.setShapeInEditor(currentShape)
  }

  private fun setCurrentShape(primitive: CustomCanvas.ShapeOption) {
    currentShape = primitive
    customCanvas.setShapeInEditor(currentShape)
    updateMenuCheckState(currentShape)
    updateToolbarIconState(currentShape)
    updateToolbarTitle(currentShape)
    showCustomNotify(currentShape)
  }

  private fun updateMenuCheckState(selectedOption: CustomCanvas.ShapeOption) {
    menuItemMap.values.forEach { mainMenu.findItem(getMenuItemId(it))?.isChecked = false }
    mainMenu.findItem(getMenuItemId(selectedOption))?.isChecked = true
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    mainMenu = menu!!

    menuItemMap[R.id.ellipseSelect] = CustomCanvas.ShapeOption.ELLIPSE
    menuItemMap[R.id.lineSelect] = CustomCanvas.ShapeOption.LINE
    menuItemMap[R.id.pointSelect] = CustomCanvas.ShapeOption.POINT
    menuItemMap[R.id.rectSelect] = CustomCanvas.ShapeOption.RECT
    menuItemMap[R.id.lineCirclesSelect] = CustomCanvas.ShapeOption.LINE_CIRCLES
    menuItemMap[R.id.cubeSelect] = CustomCanvas.ShapeOption.CUBE

    updateToolbarIconState(currentShape)
    updateMenuCheckState(currentShape)
    updateToolbarTitle(currentShape)
    showCustomNotify(currentShape)

    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.dotIcon -> setCurrentShape(CustomCanvas.ShapeOption.POINT)
      R.id.lineIcon -> setCurrentShape(CustomCanvas.ShapeOption.LINE)
      R.id.rectIcon -> setCurrentShape(CustomCanvas.ShapeOption.RECT)
      R.id.ellipseIcon -> setCurrentShape(CustomCanvas.ShapeOption.ELLIPSE)
      R.id.lineCirclesIcon -> setCurrentShape(CustomCanvas.ShapeOption.LINE_CIRCLES)
      R.id.cubeIcon -> setCurrentShape(CustomCanvas.ShapeOption.CUBE) // Handle Cube selection
      else -> {
        menuItemMap[item.itemId]?.let { setCurrentShape(it) }
      }
    }

    return super.onOptionsItemSelected(item)
  }

  private fun updateToolbarIconState(selectedShape: CustomCanvas.ShapeOption) {
    mainMenu.findItem(R.id.dotIcon).setIcon(R.drawable.point_disabled)
    mainMenu.findItem(R.id.lineIcon).setIcon(R.drawable.line_disabled)
    mainMenu.findItem(R.id.rectIcon).setIcon(R.drawable.rect_disabled)
    mainMenu.findItem(R.id.ellipseIcon).setIcon(R.drawable.ellipse_disabled)
    mainMenu.findItem(R.id.lineCirclesIcon).setIcon(R.drawable.line_circles_disabled)
    mainMenu.findItem(R.id.cubeIcon).setIcon(R.drawable.cube_disabled) // Added Cube disabled icon

    when (selectedShape) {
      CustomCanvas.ShapeOption.POINT -> mainMenu.findItem(R.id.dotIcon).setIcon(R.drawable.point)
      CustomCanvas.ShapeOption.LINE -> mainMenu.findItem(R.id.lineIcon).setIcon(R.drawable.line)
      CustomCanvas.ShapeOption.RECT -> mainMenu.findItem(R.id.rectIcon).setIcon(R.drawable.rect)
      CustomCanvas.ShapeOption.ELLIPSE -> mainMenu.findItem(R.id.ellipseIcon).setIcon(R.drawable.ellipse)
      CustomCanvas.ShapeOption.LINE_CIRCLES -> mainMenu.findItem(R.id.lineCirclesIcon).setIcon(R.drawable.line_circles)
      CustomCanvas.ShapeOption.CUBE -> mainMenu.findItem(R.id.cubeIcon).setIcon(R.drawable.cube) // Set Cube icon
    }
  }

  private fun updateToolbarTitle(selectedShape: CustomCanvas.ShapeOption) {
    val title = getShapeTitle(selectedShape)
    val iconResId = getShapeIconResId(selectedShape)

    supportActionBar?.title = title
    setToolbarIcon(iconResId)
  }

  private fun showCustomNotify(selectedShape: CustomCanvas.ShapeOption) {
    val title = getShapeTitle(selectedShape)
    val toastLayout = layoutInflater.inflate(R.layout.custom_toast, null)
    val toastIcon = toastLayout.findViewById<ImageView>(R.id.toast_icon)
    val toastMessage = toastLayout.findViewById<TextView>(R.id.toast_message)

    val iconResId = getShapeIconResId(selectedShape)

    toastIcon.setImageResource(iconResId)
    toastMessage.text = title

    val toast = Toast(this).apply {
      duration = Toast.LENGTH_SHORT
      view = toastLayout
    }
    toast.show()
  }

  private fun getShapeTitle(selectedShape: CustomCanvas.ShapeOption): String {
    return when (selectedShape) {
      CustomCanvas.ShapeOption.POINT -> getString(R.string.point)
      CustomCanvas.ShapeOption.LINE -> getString(R.string.line)
      CustomCanvas.ShapeOption.RECT -> getString(R.string.rect)
      CustomCanvas.ShapeOption.ELLIPSE -> getString(R.string.ellipse)
      CustomCanvas.ShapeOption.LINE_CIRCLES -> getString(R.string.line_circles)
      CustomCanvas.ShapeOption.CUBE -> getString(R.string.cube) // Added Cube title
    }
  }

  private fun getShapeIconResId(selectedShape: CustomCanvas.ShapeOption): Int {
    return when (selectedShape) {
      CustomCanvas.ShapeOption.POINT -> R.drawable.point
      CustomCanvas.ShapeOption.LINE -> R.drawable.line
      CustomCanvas.ShapeOption.RECT -> R.drawable.rect
      CustomCanvas.ShapeOption.ELLIPSE -> R.drawable.ellipse
      CustomCanvas.ShapeOption.LINE_CIRCLES -> R.drawable.line_circles
      CustomCanvas.ShapeOption.CUBE -> R.drawable.cube
    }
  }

  private fun setToolbarIcon(iconResId: Int) {
    supportActionBar?.apply {
      setDisplayShowHomeEnabled(true)
      setIcon(iconResId)
    }
  }

  private fun getMenuItemId(option: CustomCanvas.ShapeOption): Int {
    return when (option) {
      CustomCanvas.ShapeOption.ELLIPSE -> R.id.ellipseSelect
      CustomCanvas.ShapeOption.LINE -> R.id.lineSelect
      CustomCanvas.ShapeOption.POINT -> R.id.pointSelect
      CustomCanvas.ShapeOption.RECT -> R.id.rectSelect
      CustomCanvas.ShapeOption.LINE_CIRCLES -> R.id.lineCirclesSelect
      CustomCanvas.ShapeOption.CUBE -> R.id.cubeSelect
    }
  }
}
