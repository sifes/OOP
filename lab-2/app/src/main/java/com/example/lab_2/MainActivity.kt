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
    private var menuItemMap: MutableMap<Int, CustomCanvas.ShapeOption> = mutableMapOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        customCanvas = findViewById(R.id.customCanvas)
        customCanvas.setShapeEditor(currentShape)
    }

    private fun setCurrentShape (primitive: CustomCanvas.ShapeOption) {
        currentShape = primitive
        customCanvas.setShapeEditor(currentShape)
        updateMenuCheckState(currentShape)
    }

    private fun updateMenuCheckState (selectedOption: CustomCanvas.ShapeOption) {
        menuItemMap.values.forEach { mainMenu.findItem(getMenuItemId(it))?.isChecked = false }
        mainMenu.findItem(getMenuItemId(selectedOption))?.isChecked = true
    }

    private fun getMenuItemId (option: CustomCanvas.ShapeOption): Int {
        return when (option) {
            CustomCanvas.ShapeOption.ELLIPSE -> R.id.ellipseSelect
            CustomCanvas.ShapeOption.LINE -> R.id.lineSelect
            CustomCanvas.ShapeOption.POINT -> R.id.pointSelect
            CustomCanvas.ShapeOption.RECTANGLE -> R.id.rectSelect
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        mainMenu = menu!!

        menuItemMap[R.id.ellipseSelect] = CustomCanvas.ShapeOption.ELLIPSE
        menuItemMap[R.id.lineSelect] = CustomCanvas.ShapeOption.LINE
        menuItemMap[R.id.pointSelect] = CustomCanvas.ShapeOption.POINT
        menuItemMap[R.id.rectSelect] = CustomCanvas.ShapeOption.RECTANGLE

        updateMenuCheckState(currentShape)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val selectedPrimitive = menuItemMap[item.itemId]
        if (selectedPrimitive != null) {
            setCurrentShape(selectedPrimitive)
            updateMenuCheckState(selectedPrimitive)
        }

        return super.onOptionsItemSelected(item)
    }
}
