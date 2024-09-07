package module1

import javax.swing.*

fun showFirstModuleDialogs(onComplete: () -> Unit) {
    val frame = JFrame("Main Window First Module")
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.setSize(500, 500)
    frame.isVisible = false

    showFirstDialog(frame, onComplete)
}

private fun showFirstDialog(frame: JFrame, onComplete: () -> Unit) {
    val firstDialog = JDialog(frame, "First Dialog", true)
    val nextButton = JButton("Next >")
    val cancelButton = JButton("Cancel")
    val firstPanel = JPanel()

    nextButton.addActionListener {
        firstDialog.dispose()
        showSecondDialog(frame, onComplete)
    }

    cancelButton.addActionListener {
        firstDialog.dispose()
        onComplete()
    }

    firstPanel.add(nextButton)
    firstPanel.add(cancelButton)
    firstDialog.add(firstPanel)
    firstDialog.setSize(500, 500)
    firstDialog.isVisible = true
}

private fun showSecondDialog(frame: JFrame, onComplete: () -> Unit) {
    val secondDialog = JDialog(frame, "Second Dialog", true)
    val backButton = JButton("< Back")
    val yesButton = JButton("Yes")
    val cancelButton = JButton("Cancel")
    val secondPanel = JPanel()

    backButton.addActionListener {
        secondDialog.dispose()
        showFirstDialog(frame, onComplete)
    }

    yesButton.addActionListener {
        JOptionPane.showMessageDialog(secondDialog, "You clicked Yes!")
    }

    cancelButton.addActionListener {
        secondDialog.dispose()
        onComplete()
    }

    secondPanel.add(backButton)
    secondPanel.add(yesButton)
    secondPanel.add(cancelButton)
    secondDialog.add(secondPanel)
    secondDialog.setSize(500, 500)
    secondDialog.isVisible = true
}
