package com.github.kathybekh.doubleHashMap.view

import com.github.kathybekh.doubleHashMap.controller.DoubleHashingMapController
import javafx.geometry.Insets
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.ButtonType
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.BorderPane
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.stage.FileChooser
import tornadofx.*


class DoubleHashingMapView : View() {
    private val controller: DoubleHashingMapController by inject()

    override val root = BorderPane()
    private var keyField: TextField by singleAssign()
    private var valueField: TextField by singleAssign()
    private lateinit var statusLabel: Label

    private var rows = controller.generateRows().observable()

    init {
        title = "Double Hashing Map"

        with(root) {
            background = Background(
                BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)
            )

            top {
                statusLabel = label("Enter key and value or press help.")
                statusLabel.font = Font("Arial", 20.0)
                statusLabel.textFill = Color.web("#1a237e")
            }

            center {
                stackpane {
                    tableview(rows) {
                        readonlyColumn("Index", TableRow::id)
                        readonlyColumn("Key", TableRow::mapKey)
                        readonlyColumn("Value", TableRow::mapValue)
                        columnResizePolicy = SmartResize.POLICY
                    }
                }
            }

            right {

                    vbox(16) {
                        form {
                            fieldset {
                                field("Key") {
                                    keyField = textfield()
                                }
                                field("Value") {
                                    valueField = textfield()
                                }
                            }
                        }

                        val addButton = { imagePath: String, text: String,
                                          action: () -> Unit ->
                            {
                                stackpane {
                                    button(graphic = imageview(imagePath) {
                                        fitWidth = 150.0
                                        fitHeight = 50.0
                                    }).action {
                                        action()
                                    }

                                    text(text) {
                                        fill = Color.WHITE
                                        font = Font(20.0)
                                        isMouseTransparent = true
                                    }
                                }
                            }
                        }

                        addButton("images/green.png", "ADD")
                        {
                            if (keyField.text != "" && valueField.text != "") {
                                controller.add(keyField.text, valueField.text)
                                controller.updateTable(rows)
                                keyField.clear()
                                valueField.clear()
                                statusLabel.text = "Successful add."
                            } else {
                                statusLabel.text = "Not enough data! Enter key and value."
                            }
                        }()

                        addButton("images/blue.png", "FIND")
                        {
                            val foundValue = controller.find(keyField.text)
//                            INFORMATION
                            val alert = Alert(AlertType.CONFIRMATION)
                            alert.title = "Find Result"
                            alert.headerText = if (keyField.text != "" && foundValue != null) {
                                "$foundValue"
                            } else {
                                "The item you were looking for was not found!"
                            }
                            alert.showAndWait()
                            keyField.clear()
                            valueField.clear()
                        }()

                        addButton("images/orange.png", "DELETE") {
                            if (keyField.text != "") {
                                val alert = Alert(AlertType.CONFIRMATION)
                                alert.title = "Delete Entry"
                                alert.headerText = "Are you sure want to delete this entry?"
                                val option = alert.showAndWait()
                                if (option.get() == ButtonType.OK) {
                                    controller.delete(keyField.text)
                                    controller.updateTable(rows)
                                    statusLabel.text = "Entry was delete."
                                } else {
                                    statusLabel.text = "Entry was not delete."
                                }
                                keyField.clear()
                                valueField.clear()
                            }
                        }()

                        addButton("images/purple.png", "LOAD") {
                            val dir = chooseFile(
                                "Select File",
                                arrayOf(FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt"))
                            )
                            if (dir.isEmpty()) {
                                statusLabel.text = "File was not selected."
                                return@addButton
                            }
                            controller.readFromFile(dir.first())
                            controller.updateTable(rows)
                            statusLabel.text = "File data was moved to table."
                        }()

                        addButton("images/red.png", "CLEAR") {
                            val alert = Alert(AlertType.CONFIRMATION)
                            alert.title = "Delete All Entries"
                            alert.headerText = "Are you sure want to delete all entries?"
                            val option = alert.showAndWait()
                            if (option.get() == ButtonType.OK) {
                                controller.clear()
                                controller.updateTable(rows)
                                statusLabel.text = "Table was clear."
                            } else {
                                statusLabel.text = "Table was not clear."
                            }
                        }()

                        addButton("images/turquoise.png", "HELP") {
                            HelpWindow().openWindow()
                        }()
                    }

            }
        }
    }
}

