package com.larack.netconfig

class NativeLib {

    /**
     * A native method that is implemented by the 'netconfig' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'netconfig' library on application startup.
        init {
            System.loadLibrary("netconfig")
        }
    }
}