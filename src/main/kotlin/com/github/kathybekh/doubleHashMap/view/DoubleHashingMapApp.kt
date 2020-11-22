package com.github.kathybekh.doubleHashMap.view


import com.github.kathybekh.doubleHashMap.controller.DoubleHashingMapController
import com.github.kathybekh.doubleHashMap.model.DoubleHashingMap
import tornadofx.App
import tornadofx.launch

class DoubleHashingMapApp : App(DoubleHashingMapView:: class) { }

fun main(args: Array<String>) {
    launch<DoubleHashingMapApp>()
}