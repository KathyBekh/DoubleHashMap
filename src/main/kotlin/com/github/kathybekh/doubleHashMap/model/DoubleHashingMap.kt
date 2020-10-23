package com.github.kathybekh.doubleHashMap.model

import kotlin.math.abs

class DoubleHashingMap<K, V> : MutableMap<K, V> {

    private val defaultCapacity: Int = 16
    internal var map = arrayOfNulls<Entry<K, V>>(0)

    override var size: Int = 0
    private var tableSize = 0

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = map.filterNotNull().toMutableSet()

    override val keys: MutableSet<K>
        get() {
            val keys = mutableSetOf<K>()
            for (pair in map) {
                if (pair != null) {
                    keys.add(pair.key)
                }
            }
            return keys
        }

    override val values: MutableCollection<V>
        get() {
            val values = mutableSetOf<V>()
            for (pair in map) {
                if (pair != null) {
                    values.add(pair.value)
                }
            }
            return values
        }

    override fun containsKey(key: K): Boolean {
        return key in keys
    }

    override fun containsValue(value: V): Boolean {
        return value in values
    }

    override fun get(key: K): V? {
        return map[index(key)]?.value
    }

    override fun isEmpty(): Boolean {
        var bool = true
        for (i in map) {
            if (i != null) {
                bool = false
            }
        }
        return bool
    }

    override fun clear() {
        map = arrayOfNulls(0)
        size = 0
    }

    override fun put(key: K, value: V): V? {
        if (map.isEmpty()) {
            map = arrayOfNulls(defaultCapacity)
            tableSize = defaultCapacity
        }
        if (loadFactor() > 75.0) {
            resize()
        }
        val index = index(key)
        val oldDoubleHashingEntry = map[index]
        map[index] = Entry(key, value)
        if (oldDoubleHashingEntry == null) {
                size += 1
        }
        return oldDoubleHashingEntry?.value
    }

    override fun putAll(from: Map<out K, V>) {
        for (pair in from) {
            map[index(pair.key)] = Entry(pair.key, pair.value)
        }
    }

    override fun remove(key: K): V? {
        val index = findingIndex(key) ?: return null
        val oldDoubleHashingEntry = map[index]
        map[index] = null
        size -= 1
        return oldDoubleHashingEntry?.value
    }

    private fun index(key: K): Int {
        var ind = firstHash(key)
        if (!containsKey(key)) {
            while (map[ind] != null) {
                ind = (ind + secondHash(key)) % tableSize
            }
        }
        return ind
    }


    private fun firstHash(key: K): Int = abs(key.hashCode() % tableSize)

    private fun secondHash(key: K): Int {
        var ind = key.hashCode()
        val newInd = (19 * ind) xor ind
        ind = abs(newInd % (tableSize - 1))
        return ind
    }

    override fun toString(): String {
        var string = "keys - values \n"
        for (i in map) {
            if (i != null) {
                string = "$string $i \n"
            }
        }
        return string
    }

    private fun loadFactor(): Double {
        return size * 100.0 / tableSize
    }

    private fun resize() {
        tableSize *= 2
        val newMap = arrayOfNulls<Entry<K, V>>(tableSize)
        size = 0
        for (pair in map) {
            if (pair != null) {
                newMap[index(pair.key)] = pair
                size += 1
            }
        }
        map = newMap
    }

    private fun findingIndex(key: K): Int? {
        if (!containsKey(key)) return null
        var indexKey = index(key)
        var valueOfKeyWasInMap = map[indexKey]?.key
        if (valueOfKeyWasInMap != key) {
            while (valueOfKeyWasInMap != key) {
                indexKey = (indexKey + secondHash(key)) % tableSize
                valueOfKeyWasInMap = map[indexKey]?.key
            }
        }
         return indexKey
    }

    fun find(key: K) : Entry<K, V>? {
        val index = findingIndex(key)?: return null
        return map[index]
    }
}
