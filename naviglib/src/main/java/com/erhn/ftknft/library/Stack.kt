package com.erhn.ftknft.library

import android.util.Log
import java.util.*

class Stack(val stackName: String) {

    private val screensKeys = LinkedList<String>()


    fun push(screenKey: String) {
        screensKeys.add(screenKey)
        log("Push $screenKey")
        log(toString())
    }

    fun pop() {
        if (screensKeys.isNotEmpty()) {
            val removed = screensKeys.removeLast()
            log("Pop $removed")
            log(toString())
        }
    }

    fun popTo(screenKey: String) {
        if (screensKeys.isEmpty() || !screensKeys.contains(screenKey)) return
        var isNotRemoved = true
        while (isNotRemoved) {
            val last = screensKeys.last
            if (last== screenKey) {
                isNotRemoved = false
            } else {
                screensKeys.removeLast()
                log("Pop $last")
            }
        }
        log(toString())
    }

    fun clear() {
        screensKeys.clear()
        log("Clear")
        log(toString())
    }

    fun contains(screenKey: String): Boolean = screensKeys.contains(screenKey)

    fun isEmpty(): Boolean = screensKeys.isEmpty()

    override fun toString(): String {
        return "[ ${allScreenKeys()} ]"
    }

    private fun allScreenKeys(): String {
        var stroke: String = ""
        for (i in 0 until screensKeys.size) {
            if (i > 0) {
                stroke += " "
            }
            stroke += "[${screensKeys[i]}]"
        }
        return stroke
    }

    private fun log(msg: String) {
        if (Navigation.isShowLogs) {
            Log.d(Navigation.LOG_TAG, "$stackName: $msg")
        }
    }


}