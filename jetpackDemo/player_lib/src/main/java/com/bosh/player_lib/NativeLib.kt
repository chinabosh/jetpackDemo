package com.bosh.player_lib

class NativeLib {

    /**
     * A native method that is implemented by the 'player_lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'player_lib' library on application startup.
        init {
            System.loadLibrary("player_lib")
        }
    }
}