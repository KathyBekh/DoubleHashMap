package com.github.kathybekh.doubleHashMap.controller

import com.github.kathybekh.doubleHashMap.model.DoubleHashingMap
import com.github.kathybekh.doubleHashMap.model.Entry
import com.github.kathybekh.doubleHashMap.view.DoubleHashingMapView
import com.github.kathybekh.doubleHashMap.view.TableRow
import javafx.collections.ObservableList
import tornadofx.Controller

class DoubleHashingMapController : Controller() {
    private val view: DoubleHashingMapView by inject()
    private val workMap = DoubleHashingMap<String, String>()

    fun add(key: String, value: String) {
        workMap[key] = value
    }

    fun generateListOfRows() : List<TableRow> {
        val l = mutableListOf<TableRow>()
        var ind = 0
        for (pair in workMap.map) {
            val q = TableRow(ind, pair?.key, pair?.value)
            l.add(q)
            ind += 1
        }
        return l
    }

    fun updateTable(rows: ObservableList<TableRow>) {
        rows.clear()
        rows.addAll(generateListOfRows())
    }

    fun find(key: String): Entry<String, String>? {
        return workMap.find(key)
    }

    fun delete(key: String) {
        workMap.remove(key)
    }
}