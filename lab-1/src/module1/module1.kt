package module1

import javax.swing.*

fun showFirstModuleDialogs(onComplete: () -> Unit) {
    val frame = JFrame("Main Window First Module")
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.setSize(500, 500)
    frame.isVisible = false

    showFirstDialog(frame, onComplete)
}

// Function to display the first dialog.
private fun showFirstDialog(frame: JFrame, onComplete: () -> Unit) {
    val firstDialog = JDialog(frame, "First Dialog", true) // Creates a modal dialog (blocks interaction with other windows).
    val nextButton = JButton("Next >")
    val cancelButton = JButton("Cancel")
    val firstPanel = JPanel()

    nextButton.addActionListener {
        firstDialog.dispose() // Closes the first dialog.
        showSecondDialog(frame, onComplete) // Opens the second dialog.
    }

    cancelButton.addActionListener {
        firstDialog.dispose() // Closes the first dialog.
        onComplete() // Executes the onComplete callback.
    }

    // Add the buttons to the panel.
    firstPanel.add(nextButton)
    firstPanel.add(cancelButton)
    // Add the panel to the dialog.
    firstDialog.add(firstPanel)
    firstDialog.setSize(500, 500) // Set the size of the dialog.
    firstDialog.isVisible = true // Show the dialog.
}

// Function to display the second dialog.
private fun showSecondDialog(frame: JFrame, onComplete: () -> Unit) {
    val secondDialog = JDialog(frame, "Second Dialog", true) // Creates a modal dialog for the second step.
    val backButton = JButton("< Back")
    val yesButton = JButton("Yes")
    val cancelButton = JButton("Cancel")
    val secondPanel = JPanel()

    backButton.addActionListener {
        secondDialog.dispose() // Closes the second dialog.
        showFirstDialog(frame, onComplete) // Reopens the first dialog.
    }

    yesButton.addActionListener {
        JOptionPane.showMessageDialog(secondDialog, "You clicked Yes!")
    }

    cancelButton.addActionListener {
        secondDialog.dispose() // Closes the second dialog.
        onComplete() // Executes the onComplete callback.
    }

    // Add the buttons to the panel.
    secondPanel.add(backButton)
    secondPanel.add(yesButton)
    secondPanel.add(cancelButton)
    // Add the panel to the dialog.
    secondDialog.add(secondPanel)
    secondDialog.setSize(500, 500) // Set the size of the dialog.
    secondDialog.isVisible = true // Show the dialog.
}
