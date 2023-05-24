package no.bekk.game

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

object EngineConfig {
    const val VIEWPORT_WIDTH = 1920f
    const val VIEWPORT_HEIGHT = 1080f

    var width = 1920
    var height = 1080
    var gutterHeight: Int = 0
    var gutterWidth: Int = 0
}

val config = LwjglApplicationConfiguration().apply {
    title = "kotlin libgdx intro"
    width = EngineConfig.width
    height = EngineConfig.height
    foregroundFPS = 60
    vSyncEnabled = false
    samples = 0
    audioDeviceBufferSize = 1024
    audioDeviceSimultaneousSources = 64
}