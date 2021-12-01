package com.example.multiplerecyclerviews

class Model(var type: Int, var text: String, var data: Int) {
    companion object {
        const val TEXT_TYPE = 0
        const val IMAGE_TYPE = 1
        const val AUDIO_TYPE = 2
    }
}
