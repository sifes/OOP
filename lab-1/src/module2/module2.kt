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
        dialog.dispose()
    }

    panel.add(JScrollPane(list))
    panel.add(yesButton)
    panel.add(cancelButton)
    panel.add(messageLabel)

    dialog.add(panel)
    dialog.setSize(400, 400)
    dialog.isVisible = true
}
