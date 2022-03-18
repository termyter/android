package com.termyter.lab2

import java.io.Serializable

data class Anime(
    val imageId: String = "", var name: String = "", var desc: String = "",
) : Serializable
