package com.example.lab_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    private lateinit var customCanvas: CustomCanvas
    private var currentShape: CustomCanvas.ShapeOption = CustomCanvas.ShapeOption.POINT
    private lateinit var mainMenu: Menu
    private val menuItemMap: MutableMap<Int, CustomCanvas.ShapeOption> = mutableMapOf(
        R.id.ellipseSelect to CustomCanvas.ShapeOption.ELLIPSE,
        R.id.lineSelect to CustomCanvas.ShapeOption.LINE,
        R.id.pointSelect to CustomCanvas.ShapeOption.POINT,
        R.id.rectSelect to CustomCanvas.ShapeOption.RECTANGLE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        customCanvas = findViewById(R.id.customCanvas)
        customCanvas.setShapeEditor(currentShape)
    }

    private fun setCurrentShape(primitive: CustomCanvas.ShapeOption) {
        currentShape = primitive
        customCanvas.setShapeEditor(currentShape)
        updateMenuCheckState(currentShape)
    }

    private fun updateMenuCheckState(selectedOption: CustomCanvas.ShapeOption) {
        menuItemMap.keys.forEach { menuItemId ->
            mainMenu.findItem(menuItemId)?.isChecked = false
        }

        val selectedMenuItemId = menuItemMap.entries.find { it.value == selectedOption }?.key
        selectedMenuItemId?.let {
            mainMenu.findItem(it)?.isChecked = true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        mainMenu = menu!!

        updateMenuCheckState(currentShape)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val selectedPrimitive = menuItemMap[item.itemId]
        if (selectedPrimitive != null) {
            setCurrentShape(selectedPrimitive)
        }

        return super.onOptionsItemSelected(item)
    }
}
