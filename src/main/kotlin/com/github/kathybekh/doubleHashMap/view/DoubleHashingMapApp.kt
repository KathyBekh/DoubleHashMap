package com.github.kathybekh.doubleHashMap.view

import com.github.kathybekh.doubleHashMap.model.DoubleHashingMap
import tornadofx.App

class DoubleHashingMapApp : App(DoubleHashingMapView:: class) { }

fun main() {
    val map = DoubleHashingMap<String, String>()
    map["for"] = "some"
    map.put("two", "any")

    println("values:")
    for (p in map.values) {
        println(p)
    }
    println("keys:")
    for (p in map.keys) {
        println(p)
    }
    map["tree"] = "ololo"
    map["one"] = "ololo"
    map["five"] = "enthu"

    println(map)
    println("" + map["two"])
    println(map.get("for"))
}