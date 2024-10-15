package com.example.lab_3

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

        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Initialize the custom canvas and set the default shape
        customCanvas = findViewById(R.id.customCanvas)
        customCanvas.setShapeEditor(currentShape)
    }

    // Sets the current shape and updates the UI components accordingly
    private fun setCurrentShape(primitive: CustomCanvas.ShapeOption) {
        currentShape = primitive
        customCanvas.setShapeEditor(currentShape)
        updateMenuCheckState(currentShape)
        updateToolbarIconState(currentShape)
        updateToolbarTitle(currentShape)
        showCustomNotify(currentShape)
    }

    // Updates the checked state of menu items based on the selected shape
    private fun updateMenuCheckState(selectedOption: CustomCanvas.ShapeOption) {
        // Uncheck all menu items
        menuItemMap.values.forEach { mainMenu.findItem(getMenuItemId(it))?.isChecked = false }
        // Check the selected menu item
        mainMenu.findItem(getMenuItemId(selectedOption))?.isChecked = true
    }

    // Creates the options menu and initializes the shape options
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        mainMenu = menu!!

        // Map menu items to shape options
        menuItemMap[R.id.ellipseSelect] = CustomCanvas.ShapeOption.ELLIPSE
        menuItemMap[R.id.lineSelect] = CustomCanvas.ShapeOption.LINE
        menuItemMap[R.id.pointSelect] = CustomCanvas.ShapeOption.POINT
        menuItemMap[R.id.rectSelect] = CustomCanvas.ShapeOption.RECTANGLE

        // Initialize the toolbar and menu with the current shape
        updateToolbarIconState(currentShape)
        updateMenuCheckState(currentShape)
        updateToolbarTitle(currentShape)
        showCustomNotify(currentShape)

        return true
    }

    // Handles menu item selection and updates the current shape accordingly
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.dotIcon -> setCurrentShape(CustomCanvas.ShapeOption.POINT)
            R.id.lineIcon -> setCurrentShape(CustomCanvas.ShapeOption.LINE)
            R.id.rectangleIcon -> setCurrentShape(CustomCanvas.ShapeOption.RECTANGLE)
            R.id.ellipseIcon -> setCurrentShape(CustomCanvas.ShapeOption.ELLIPSE)
            else -> {
                menuItemMap[item.itemId]?.let { setCurrentShape(it) }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    // Updates the toolbar icons based on the selected shape
    private fun updateToolbarIconState(selectedShape: CustomCanvas.ShapeOption) {
        mainMenu.findItem(R.id.dotIcon).setIcon(R.drawable.point_disabled)
        mainMenu.findItem(R.id.lineIcon).setIcon(R.drawable.line_disabled)
        mainMenu.findItem(R.id.rectangleIcon).setIcon(R.drawable.rect_disabled)
        mainMenu.findItem(R.id.ellipseIcon).setIcon(R.drawable.ellipse_disabled)

        when (selectedShape) {
            CustomCanvas.ShapeOption.POINT -> mainMenu.findItem(R.id.dotIcon).setIcon(R.drawable.point)
            CustomCanvas.ShapeOption.LINE -> mainMenu.findItem(R.id.lineIcon).setIcon(R.drawable.line)
            CustomCanvas.ShapeOption.RECTANGLE -> mainMenu.findItem(R.id.rectangleIcon).setIcon(R.drawable.rect)
            CustomCanvas.ShapeOption.ELLIPSE -> mainMenu.findItem(R.id.ellipseIcon).setIcon(R.drawable.ellipse)
        }
    }

    // Updates the toolbar title and icon based on the selected shape
    private fun updateToolbarTitle(selectedShape: CustomCanvas.ShapeOption) {
        val title = getShapeTitle(selectedShape)
        val iconResId = getShapeIconResId(selectedShape)

        supportActionBar?.title = title
        setToolbarIcon(iconResId)
    }

    // Displays a custom toast message indicating the selected shape
    private fun showCustomNotify(selectedShape: CustomCanvas.ShapeOption) {
        val title = getShapeTitle(selectedShape)
        val toastLayout = layoutInflater.inflate(R.layout.custom_toast, null)
        val toastIcon = toastLayout.findViewById<ImageView>(R.id.toast_icon)
        val toastMessage = toastLayout.findViewById<TextView>(R.id.toast_message)

        val iconResId = getShapeIconResId(selectedShape)

        toastIcon.setImageResource(iconResId)
        toastMessage.text = title

        // Show the custom toast
        val toast = Toast(this).apply {
            duration = Toast.LENGTH_SHORT
            view = toastLayout
        }
        toast.show()
    }

    // Gets the title of the selected shape for display purposes
    private fun getShapeTitle(selectedShape: CustomCanvas.ShapeOption): String {
        return when (selectedShape) {
            CustomCanvas.ShapeOption.POINT -> "Крапка"
            CustomCanvas.ShapeOption.LINE -> "Лінія"
            CustomCanvas.ShapeOption.RECTANGLE -> "Прямокутник"
            CustomCanvas.ShapeOption.ELLIPSE -> "Елліпс"
        }
    }

    // Gets the resource ID of the icon associated with the selected shape
    private fun getShapeIconResId(selectedShape: CustomCanvas.ShapeOption): Int {
        return when (selectedShape) {
            CustomCanvas.ShapeOption.POINT -> R.drawable.point
            CustomCanvas.ShapeOption.LINE -> R.drawable.line
            CustomCanvas.ShapeOption.RECTANGLE -> R.drawable.rect
            CustomCanvas.ShapeOption.ELLIPSE -> R.drawable.ellipse
        }
    }

    // Sets the icon for the toolbar based on the selected shape
    private fun setToolbarIcon(iconResId: Int) {
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setIcon(iconResId)
        }
    }

    // Gets the menu item ID corresponding to the specified shape option
    private fun getMenuItemId(option: CustomCanvas.ShapeOption): Int {
        return when (option) {
            CustomCanvas.ShapeOption.ELLIPSE -> R.id.ellipseSelect
            CustomCanvas.ShapeOption.LINE -> R.id.lineSelect
            CustomCanvas.ShapeOption.POINT -> R.id.pointSelect
            CustomCanvas.ShapeOption.RECTANGLE -> R.id.rectSelect
        }
    }
}
