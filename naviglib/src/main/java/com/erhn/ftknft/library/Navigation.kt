package com.erhn.ftknft.library

object Navigation {
    internal var isShowLogs: Boolean = true

    const val LOG_TAG = "NAVIGATION"

    fun enableLog(isEnable: Boolean) {
        isShowLogs = isEnable
    }

}