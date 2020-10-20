package com.github.kathybekh.doubleHashMap.view

import com.github.kathybekh.doubleHashMap.controller.DoubleHashingMapController
import javafx.geometry.Insets
import javafx.scene.control.TextField
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import tornadofx.*

class DoubleHashingMapView : View() {
    private val controller: DoubleHashingMapController by inject()

    override val root = HBox()
    private var keyField: TextField by singleAssign()
    private var valueField: TextField by singleAssign()

    private var rows = controller.generateListOfRows().observable()

    init {
        title = "Double Hashing Map"

        with(root) {
            background = Background(
                BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)
            )
                    stackpane {
                        tableview(rows) {
                            readonlyColumn("Index", TableRow::id)
                            readonlyColumn("Key", TableRow::mapKey)
                            readonlyColumn("Value", TableRow::mapValue)
                            columnResizePolicy = SmartResize.POLICY
                        }
                    }

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
                            if (keyField.text != "") {
                                controller.add(keyField.text, valueField.text)
                                controller.updateTable(rows)
                            }
                        }()

                        addButton("images/blue.png", "FIND")
                        {
                            if (keyField.text != "") {
                                println(controller.find(keyField.text))
                            } else { println("The item you were looking for was not found!")}
                        }()

                        addButton("images/orange.png", "DELETE") {
                            if (keyField.text != "") {
                                controller.delete(keyField.text)
                                controller.updateTable(rows)
                            }
                        }()

            }
        }
    }
}

