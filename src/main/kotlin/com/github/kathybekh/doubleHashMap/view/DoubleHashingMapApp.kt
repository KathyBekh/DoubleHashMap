package com.github.kathybekh.doubleHashMap.view


import tornadofx.App
import tornadofx.launch

class DoubleHashingMapApp : App(DoubleHashingMapView:: class)

fun main() {
    launch<DoubleHashingMapApp>()
}