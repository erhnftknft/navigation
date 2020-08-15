package com.erhn.ftknft.library.core

import androidx.fragment.app.Fragment


abstract class Screen(val screenTag: String) {
    abstract fun createFragment(): Fragment
}