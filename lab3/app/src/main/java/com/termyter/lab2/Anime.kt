package com.termyter.lab2

import java.io.Serializable

data class Anime(
    val imageId: Int = 0, var name: String = "", val desc: String = "",
) : Serializable
