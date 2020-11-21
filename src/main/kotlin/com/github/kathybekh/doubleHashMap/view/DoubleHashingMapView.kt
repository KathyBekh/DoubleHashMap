package com.github.kathybekh.doubleHashMap.view

import com.github.kathybekh.doubleHashMap.controller.DoubleHashingMapController
import javafx.geometry.Insets
import javafx.scene.control.TextField
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.stage.FileChooser
import tornadofx.*

class DoubleHashingMapView : View() {
    private val controller: DoubleHashingMapController by inject()

    override val root = BorderPane()
    private var keyField: TextField by singleAssign()
    private var valueField: TextField by singleAssign()

    private var rows = controller.generateListOfRows().observable()

    init {
        title = "Double Hashing Map"

        with(root) {
            background = Background(
                BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)
            )
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
                            } else {
                                println("Not enough data! Enter key and value.")
                            }
                        }()

                        addButton("images/blue.png", "FIND")
                        {
                            val foundValue = controller.find(keyField.text)
                            if (keyField.text != "" && foundValue != null) {
                                println(foundValue)
                            } else {
                                println("The item you were looking for was not found!")
                            }
                        }()

                        addButton("images/orange.png", "DELETE") {
                            if (keyField.text != "") {
                                controller.delete(keyField.text)
                                controller.updateTable(rows)
                            }
                        }()

                        addButton("images/yellow.png", "LOAD") {
                            val dir = chooseFile(
                                "Select File",
                                arrayOf(FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt"))
                            )
                            if (dir.isEmpty()) {
                                return@addButton
                            }
                            controller.readFromFile(dir.first())
                            controller.updateTable(rows)
                        }()
                    }

            }
        }
    }
}

