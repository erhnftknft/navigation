package com.erhn.ftknft.library.core

/**
 *
 * An interface that describes the minimal commands for switching screens
 *
 */
interface Navigator {

    /**
     * Replaces the current screen with a new one.
     *
     * @param[screen] screen that will replace the current.
     *
     */
    fun replace(screen: Screen)

    /**
     * Adds a new screen without replacement after the current one.
     *
     * @param[screen] Screen to be added.
     *
     */
    fun forward(screen: Screen)

    /**
     * Returns to the previous screen if any.
     *
     */
    fun back()

    /**
     * Returns the screen with tag @param[screenTag]
     * If @param[screenTag] set to null then removes all screens.
     *
     * @param[screenTag] the screen to return to.
     *
     */
    fun backTo(screenTag: String?)
}