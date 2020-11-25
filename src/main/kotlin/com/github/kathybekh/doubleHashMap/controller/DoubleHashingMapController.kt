package com.github.kathybekh.doubleHashMap.controller

import com.github.kathybekh.doubleHashMap.model.DoubleHashingMap
import com.github.kathybekh.doubleHashMap.model.Entry
import com.github.kathybekh.doubleHashMap.view.TableRow
import javafx.collections.ObservableList
import tornadofx.Controller
import java.io.File

class DoubleHashingMapController : Controller() {
    private val workMap = DoubleHashingMap<String, String>()

    internal fun add(key: String, value: String) {
        workMap[key] = value
    }

//    Generate table rows to display in GUI.
    internal fun generateRows() : List<TableRow> {
        val listOfRows = mutableListOf<TableRow>()
        var ind = 0
        for (pair in workMap) {
            val row = TableRow(ind++, pair.key, pair.value)
            listOfRows.add(row)
        }
        return listOfRows
    }

    internal fun updateTable(rows: ObservableList<TableRow>) {
        rows.clear()
        rows.addAll(generateRows())
    }

    internal fun find(key: String): Entry<String, String>? {
        return workMap.find(key)
    }

    internal fun delete(key: String) {
        workMap.remove(key)
    }

//   File format: *.txt , is specified in DoubleHashingMapView, chooseFile()
//   File line format: key - value
    internal fun readFromFile(file: File) {
        file.forEachLine { line ->
            val parseLine = line.split(" - ")
            if (parseLine.size >= 2) {
                workMap[parseLine[0]] = parseLine[1]
            }
        }
    }

    internal fun clear() {
        workMap.clear()
    }

    internal fun entries(): MutableSet<MutableMap.MutableEntry<String, String>> {
        return workMap.entries
    }
}