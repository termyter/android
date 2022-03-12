package com.termyter.lab2

import java.io.Serializable

data class Anime(
    val imageId: Int = 0, val name: String = "", val desc: String = "",
) : Serializable
