package com.github.kathybekh.doubleHashMap.view

import com.github.kathybekh.doubleHashMap.controller.DoubleHashingMapController
import javafx.geometry.Insets
import javafx.scene.image.ImageView
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.BorderPane
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color
import tornadofx.*

class DoubleHashingMapView : View() {
    private val controller: DoubleHashingMapController by inject()

    override val root = BorderPane()

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
                vbox {
                    form {
                        fieldset {
                            field("Key") {
                                textfield()
                            }
                            field("Value") {
                                textfield()
                            }
                        }
                    }
                    button(graphic = ImageView("images/add.png").apply {
                        fitWidth = 120.0
                        fitHeight = 50.0
                    })
                    button(graphic = ImageView("images/find.png").apply {
                        fitWidth = 120.0
                        fitHeight = 50.0
                    })

                    button(graphic = ImageView("images/delete.png").apply {
                        fitWidth = 120.0
                        fitHeight = 50.0
                    })
                }
            }
        }
    }
}

