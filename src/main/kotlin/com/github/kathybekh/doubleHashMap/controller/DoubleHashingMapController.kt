package com.github.kathybekh.doubleHashMap.controller

import com.github.kathybekh.doubleHashMap.model.DoubleHashingMap
import com.github.kathybekh.doubleHashMap.view.DoubleHashingMapView
import com.github.kathybekh.doubleHashMap.view.TableRow
import tornadofx.Controller

class DoubleHashingMapController : Controller() {
    private val view: DoubleHashingMapView by inject()
    val myMap = DoubleHashingMap<String, String>()

    fun display() : List<TableRow> {
        myMap["fook"] = "pook"
        myMap["haha"] = "smeshno"

        val l = mutableListOf<TableRow>()
        var ind = 0
        for (pair in myMap.map) {
            val q = TableRow(ind, pair?.key, pair?.value)
            l.add(q)
            ind += 1
        }
        return l
    }
}