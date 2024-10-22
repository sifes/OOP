package com.example.lab_5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import com.example.lab_5.shapeHelper.ShapeData
import com.example.lab_5.shapeHelper.ShapeViewModel
import com.example.lab_5.table.MyTable

class MainActivity : AppCompatActivity() {
  private lateinit var customCanvas: CustomCanvas
  private var currentShape: CustomCanvas.ShapeOption = CustomCanvas.ShapeOption.POINT
  private lateinit var mainMenu: Menu
  private lateinit var shapeInfoMap: Map<CustomCanvas.ShapeOption, ShapeInfo>
  private var currentToast: Toast? = null

  private val menuItemMap: MutableMap<Int, CustomCanvas.ShapeOption> = mutableMapOf(
    R.id.ellipseSelect to CustomCanvas.ShapeOption.ELLIPSE,
    R.id.lineSelect to CustomCanvas.ShapeOption.LINE,
    R.id.pointSelect to CustomCanvas.ShapeOption.POINT,
    R.id.rectSelect to CustomCanvas.ShapeOption.RECT,
    R.id.cubeSelect to CustomCanvas.ShapeOption.CUBE,
    R.id.lineCirclesSelect to CustomCanvas.ShapeOption.LINE_CIRCLES,
  )

  data class ShapeInfo(
    val iconId: Int,
    val title: String,
    val enabledIconResId: Int,
    val disabledIconResId: Int
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val toolbar: Toolbar = findViewById(R.id.toolbar)
    setSupportActionBar(toolbar)

    shapeInfoMap = mapOf(
      CustomCanvas.ShapeOption.POINT to ShapeInfo(R.id.pointIcon, getString(R.string.point), R.drawable.point, R.drawable.point_disabled),
      CustomCanvas.ShapeOption.LINE to ShapeInfo(R.id.lineIcon, getString(R.string.line), R.drawable.line, R.drawable.line_disabled),
      CustomCanvas.ShapeOption.RECT to ShapeInfo(R.id.rectIcon, getString(R.string.rect), R.drawable.rect, R.drawable.rect_disabled),
      CustomCanvas.ShapeOption.ELLIPSE to ShapeInfo(R.id.ellipseIcon, getString(R.string.ellipse), R.drawable.ellipse, R.drawable.ellipse_disabled),
      CustomCanvas.ShapeOption.CUBE to ShapeInfo(R.id.cubeIcon, getString(R.string.cube), R.drawable.cube, R.drawable.cube_disabled),
      CustomCanvas.ShapeOption.LINE_CIRCLES to ShapeInfo(R.id.lineCirclesIcon, getString(R.string.line_circles), R.drawable.line_circles, R.drawable.line_circles_disabled)
    )

    customCanvas = findViewById(R.id.customCanvas)
    customCanvas.setShapeEditor(currentShape)
    val myTable: MyTable = findViewById(R.id.myTable)

    val shapeViewModel: ShapeViewModel = ViewModelProvider(this)[ShapeViewModel::class.java]
    val shapesData = customCanvas.getShapesData()
    myTable.updateShapesData(shapesData)

    shapeViewModel.shapesData.observe(this) { shapes ->
      myTable.updateShapesData(shapes)
    }

    customCanvas.onShapeDataChangedListener = object : CustomCanvas.OnShapeDataChangedListener {
      override fun onShapeDataChanged(shapesData: List<ShapeData>) {
        shapeViewModel.updateShapesData(shapesData)
      }
    }

    showSystemBars()
  }

  private fun setCurrentShape(primitive: CustomCanvas.ShapeOption) {
    if (currentShape != primitive) {
      currentShape = primitive
      customCanvas.setShapeEditor(currentShape)
      updateMenuCheckState(currentShape)
      updateToolbar(currentShape)
    }
    showCustomNotify(currentShape)
  }

  private fun updateMenuCheckState(selectedOption: CustomCanvas.ShapeOption) {
    val selectedMenuItemId = menuItemMap.entries.find { it.value == selectedOption }?.key
    menuItemMap.keys.forEach { menuItemId ->
      mainMenu.findItem(menuItemId)?.isChecked = (menuItemId == selectedMenuItemId)
    }
  }


  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    mainMenu = menu!!

    val toggleTableMenuButton = mainMenu.findItem(R.id.toggleTableMenuButton)
    val toggleTableButton: Button = findViewById(R.id.toggleTableButton)
    val myTableScroll: ScrollView = findViewById(R.id.myTableScroll)

    toggleTableButton.setOnClickListener {
      toggleTableVisibility(myTableScroll, toggleTableButton, toggleTableMenuButton)
    }

    toggleTableMenuButton.setOnMenuItemClickListener {
      toggleTableVisibility(myTableScroll, toggleTableButton, toggleTableMenuButton)
      true
    }

    updateToolbar(currentShape)
    updateMenuCheckState(currentShape)
    showCustomNotify(currentShape)

    return true
  }

  private fun toggleTableVisibility(myTableScroll: ScrollView, toggleTableButton: Button, toggleTableMenuButton: MenuItem ){
    if (myTableScroll.visibility == View.GONE) {
      myTableScroll.visibility = View.VISIBLE
      toggleTableButton.text = "Hide Table"
      toggleTableMenuButton.title = "Hide Table"
    } else {
      myTableScroll.visibility = View.GONE
      toggleTableMenuButton.title = "Show Table"
      toggleTableButton.text = "Show Table"
    }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val selectedPrimitive = menuItemMap[item.itemId]
    if (selectedPrimitive == null) {
      for ((shapeOption, shapeInfo) in shapeInfoMap) {
        if (shapeInfo.iconId == item.itemId) {
          setCurrentShape(shapeOption)
          return true
        }
      }
    } else {
      setCurrentShape(selectedPrimitive)
      return true
    }

    return super.onOptionsItemSelected(item)
  }

  private fun updateToolbar(selectedShape: CustomCanvas.ShapeOption) {
    val selectedShapeInfo = shapeInfoMap[selectedShape]

    shapeInfoMap.values.forEach { shapeInfo ->
      mainMenu.findItem(shapeInfo.iconId).setIcon(shapeInfo.disabledIconResId)
    }

    selectedShapeInfo?.let { shapeInfo ->
      supportActionBar?.apply {
        title = shapeInfo.title
        setDisplayShowHomeEnabled(true)
        setIcon(shapeInfo.enabledIconResId)
      }
      mainMenu.findItem(shapeInfo.iconId).setIcon(shapeInfo.enabledIconResId)
    }
  }



  private fun showCustomNotify(selectedShape: CustomCanvas.ShapeOption) {
    shapeInfoMap[selectedShape]?.let { shapeInfo ->
      currentToast?.cancel()
      val toastLayout = layoutInflater.inflate(R.layout.custom_toast, null)
      val toastIcon = toastLayout.findViewById<ImageView>(R.id.toast_icon)
      val toastMessage = toastLayout.findViewById<TextView>(R.id.toast_message)
      toastIcon.setImageResource(shapeInfo.enabledIconResId)
      toastMessage.text = shapeInfo.title
      currentToast = Toast(this).apply {
        duration = Toast.LENGTH_SHORT
        view = toastLayout
      }
      currentToast?.show()
    }
  }

  private fun showSystemBars() {
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(window, customCanvas).show(WindowInsetsCompat.Type.systemBars())
  }
}
