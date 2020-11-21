package com.github.kathybekh.doubleHashMap.model

import kotlin.math.abs

/**
 * Provides a skeletal implementation of the [MutableMap] interface.
 * @param K the type of map keys. The map is invariant in its key type.
 * @param V the type of map values. The map is invariant in its value type.
 */

class DoubleHashingMap<K, V> : MutableMap<K, V> {

    private val defaultCapacity: Int = 16
    private var entryStorage = arrayOfNulls<Entry<K, V>>(0)

    override var size: Int = 0
    private var tableSize = 0

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = entryStorage.filterNotNull().toMutableSet()

    override val keys: MutableSet<K>
        get() {
            val keys = mutableSetOf<K>()
            for (pair in entryStorage) {
                if (pair != null) {
                    keys.add(pair.key)
                }
            }
            return keys
        }

    override val values: MutableCollection<V>
        get() {
            val values = mutableSetOf<V>()
            for (pair in entryStorage) {
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
        val index = findIndex(key)
        return if (index == null) {
            null
        } else {
            entryStorage[index]?.value
        }
    }

    override fun isEmpty(): Boolean {
        return entryStorage.all { entry -> entry == null }
    }

    override fun clear() {
        entryStorage = arrayOfNulls(0)
        size = 0
        tableSize = defaultCapacity
    }

    override fun put(key: K, value: V): V? {
        if (entryStorage.isEmpty()) {
            entryStorage = arrayOfNulls(defaultCapacity)
            tableSize = defaultCapacity
        }
        if (loadFactor() > 75.0) {
            resize()
        }

        val existingKeyIndex = findIndex(key)
        return if (existingKeyIndex == null) {
            entryStorage[newIndex(key)] = Entry(key, value)
            size += 1
            null
        } else {
            val oldValue = entryStorage[existingKeyIndex]!!.value
            entryStorage[existingKeyIndex] = Entry(key, value)
            return oldValue
        }
    }

    override fun putAll(from: Map<out K, V>) {
        for (pair in from) {
            entryStorage[newIndex(pair.key)] = Entry(pair.key, pair.value)
        }
    }

    override fun remove(key: K): V? {
        val index = findIndex(key) ?: return null
        val oldDoubleHashingEntry = entryStorage[index]
        entryStorage[index] = null
        size -= 1
        return oldDoubleHashingEntry?.value
    }

    /** Generates new index under assumptions that
     * 1) there is empty space in entryStorage
     * 2) key is not in entryStorage
     */
    private fun newIndex(key: K): Int {
        var index = firstHash(key) % tableSize
        if (entryStorage[index] == null) {
            return index
        }

        val shift = secondHash(key)
        do {
            index = (index + shift + 1) % tableSize
        } while (entryStorage[index] != null)
        return index
    }


    private fun firstHash(key: K): Int = abs(key.hashCode() % tableSize)

    private fun secondHash(key: K): Int {
        var ind = key.hashCode()
        val newInd = (19 * ind) xor ind
        ind = abs(newInd % (tableSize - 1))
        return ind
    }

    override fun toString(): String {
        return entryStorage.filterNotNull().joinToString(", ", "{", "}")
    }

    private fun loadFactor(): Double {
        return size * 100.0 / tableSize
    }

    /**
     * This method changes the size of the storage by doubling it.
     */
    private fun resize() {
        tableSize *= 2
        val oldMap = entryStorage
        entryStorage = arrayOfNulls(tableSize)
        size = 0
        for (pair in oldMap) {
            if (pair != null) {
                entryStorage[newIndex(pair.key)] = pair
                size += 1
            }
        }
    }

    private fun findIndex(key: K): Int? {
        if (!containsKey(key)) return null

        var index = firstHash(key) % tableSize
        if (entryStorage[index]?.key == key) {
            return index
        }

        val shift = secondHash(key)
        do {
            index = (index + shift) % tableSize
        } while (entryStorage[index]?.key != key)
        return index
    }

    internal fun find(key: K): Entry<K, V>? {
        val index = findIndex(key) ?: return null
        return entryStorage[index]
    }
}
