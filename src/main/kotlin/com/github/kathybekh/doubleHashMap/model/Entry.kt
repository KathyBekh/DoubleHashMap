package com.github.kathybekh.doubleHashMap.model

class Entry<K, V>(override val key: K, override var value: V) : MutableMap.MutableEntry<K, V> {

        override fun setValue(v: V): V {
            val oldValue = value
            value = v
            return oldValue
        }

    override fun toString(): String {
        return "$key - $value"
    }

}