package com.github.kathybekh.doubleHashMap.model

class DoubleHashingMap<K, V> : MutableMap<K, V> {

    private val defaultCapacity: Int = 16
    internal var map = arrayOfNulls<Entry<K, V>>(0)

    override var size: Int = 0

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
        map = arrayOfNulls<Entry<K, V>>(0)
        size = 0
    }

    override fun put(key: K, value: V): V? {
        if (map.isEmpty()) {
            map = arrayOfNulls(defaultCapacity)
        }
        if (loadFactor() > 75.0) {
            resize()
        }
        val index = index(key)
        val oldDoubleHashingEntry = map[index]
        map[index] = Entry(key, value)
        size += 1
        return oldDoubleHashingEntry?.value
    }

    override fun putAll(from: Map<out K, V>) {
        TODO("Not yet implemented")
    }

    override fun remove(key: K): V? {
        val index = index(key)
        val oldDoubleHashingEntry = map[index]
        map[index] = null
        size -= 1
        return oldDoubleHashingEntry?.value
    }

    private fun index(k: K): Int {
        return k.hashCode() % map.size
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
        return size * 100.0 / map.size
    }

    private fun resize() {
        val newMap = arrayOfNulls<Entry<K, V>>(map.size * 2)
        size = 0
        for (pair in map) {
            if (pair != null) {
                newMap[index(pair.key)] = pair
            }
        }
        map = newMap
    }
}
