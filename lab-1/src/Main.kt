package main

import module1.showFirstModuleDialogs
import module2.showSecondModuleDialog

fun main() {
    showFirstModuleDialogs {
        showSecondModuleDialog()
    }
}
