package com.example.booklisting

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ImageLinks(
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String
) {
}