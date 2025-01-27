package com.example.audiobook.repository

object PodcastMapperManager {
    private val podcasstMap: MutableMap<String?, Boolean> = mutableMapOf()

    // Function to add a new key-value pair to the map
    fun addValue(key: String?, value: Boolean) {
        podcasstMap[key] = value
    }

    // Function to retrieve a value by key
    fun getValue(key: String?): Boolean? {
        return podcasstMap[key]
    }

    // Function to update a value for an existing key
    fun updateValue(key: String?, newValue: Boolean): Boolean {
        return if (podcasstMap.containsKey(key)) {
            podcasstMap[key] = newValue
            true // Update successful
        } else {
            false // Key does not exist
        }
    }

    // Function to get the entire map (optional, if needed)
    fun getAllValues(): Map<String?, Boolean> {
        return podcasstMap
    }
}