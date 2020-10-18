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

    private val rows = controller.display().observable()
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
                        readonlyColumn("ID", TableRow::id)
                        readonlyColumn("Key", TableRow::mapKey)
                        readonlyColumn("Value", TableRow::mapValue)
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
//                    button(text = "bylia", graphic = imageview("images/add.png") {
                    stackpane {
                        button(graphic = imageview("images/green.png") {
                            fitWidth = 150.0
                            fitHeight = 50.0
                        }) {
                            action {
                                controller.myMap[keyField.text] = valueField.text
                                controller.display()
                                println("key = ${keyField.text}")
                                println("value = ${valueField.text}")
                            }
                        }

                        text("Добавить") {
                            fill = Color.WHITE
                            font = Font(20.0)
                        }

                    }

                    button(graphic = ImageView("images/blue.png").apply {
                        fitWidth = 150.0
                        fitHeight = 50.0
                    }).action {
                        println("olololo")
                    }

                    button(graphic = ImageView("images/orange.png").apply {
                        fitWidth = 150.0
                        fitHeight = 50.0
                    })
                }
            }
        }
    }
}

