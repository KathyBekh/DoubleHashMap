package com.github.kathybekh.doubleHashMap.view

import com.github.kathybekh.doubleHashMap.controller.DoubleHashingMapController
import javafx.geometry.Insets
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.BorderPane
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color
import javafx.scene.text.Font
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
            top { }

            left {
                stackpane {
                    tableview(rows) {
                        readonlyColumn("Index", TableRow::id)
                        readonlyColumn("Key", TableRow::mapKey)
                        readonlyColumn("Value", TableRow::mapValue).remainingWidth()
                        columnResizePolicy = SmartResize.POLICY
                    }
                }
            }

            center {
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

                    stackpane {
                        button(graphic = imageview("images/green.png") {
                            fitWidth = 150.0
                            fitHeight = 50.0
                        }).action {
                            if (keyField.text != "") {
                                controller.add(keyField.text, valueField.text)
                                controller.updateTable(rows)
                            }
                        }

                        text("ADD") {
                            fill = Color.WHITE
                            font = Font(20.0)
                            isMouseTransparent = true
                        }
                    }

                    stackpane {
                        button(graphic = imageview("images/blue.png") {
                            fitWidth = 150.0
                            fitHeight = 50.0
                        }).action {
                            if (keyField.text != "") {
                                println(controller.find(keyField.text))
                            }
                        }

                        text("FIND") {
                            fill = Color.WHITE
                            font = Font(20.0)
                            isMouseTransparent = true
                        }
                    }

                    stackpane {
                        button(graphic = imageview("images/orange.png") {
                            fitWidth = 150.0
                            fitHeight = 50.0
                        }).action {
                            if (keyField.text != "") {
                                controller.delete(keyField.text)
                                controller.updateTable(rows)
                            }
                        }

                        text("DELETE") {
                            fill = Color.WHITE
                            font = Font(20.0)
                            isMouseTransparent = true
                        }
                    }
                }
            }
        }
    }
}

