package module2

import javax.swing.*

fun showSecondModuleDialog() {
    val frame = JFrame("Main Window Second Module")
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.setSize(500, 500)
    frame.isVisible = false

    val dialog = JDialog(frame, "Second Dialog", true)

    val groupNames = arrayOf("IM", "ІП", "IС", "IА" , "IК", "IО")

    val list = JList(groupNames)

    val yesButton = JButton("Yes")
    val cancelButton = JButton("Cancel")

    val messageLabel = JLabel()

    val panel = JPanel()

    yesButton.addActionListener {
        val selectedGroup = list.selectedValue

        if (selectedGroup != null) {
            messageLabel.text = "Selected: $selectedGroup"
        } else {
            messageLabel.text = "No group selected"
        }
    }

    cancelButton.addActionListener {
        dialog.dispose() // Close the dialog when "Cancel" is clicked.
    }

    panel.add(JScrollPane(list)) // Add the list inside a scroll pane (to handle large lists).
    panel.add(yesButton) // Add the "Yes" button to the panel.
    panel.add(cancelButton) // Add the "Cancel" button to the panel.
    panel.add(messageLabel) // Add the label to display messages.

    dialog.add(panel)

    dialog.setSize(400, 400)
    dialog.isVisible = true // Show the dialog.
}
