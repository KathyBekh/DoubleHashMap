package com.github.kathybekh.doubleHashMap.view

import com.github.kathybekh.doubleHashMap.model.DoubleHashingMap
import tornadofx.App
import tornadofx.launch
import kotlin.math.abs

class DoubleHashingMapApp : App(DoubleHashingMapView:: class) { }

fun main(args: Array<String>) {
    launch<DoubleHashingMapApp>()
//
//    val map = DoubleHashingMap<String, String>()

}