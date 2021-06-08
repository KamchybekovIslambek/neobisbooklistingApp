package com.example.booklisting

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("volumeInfo")
    @Expose
    var volumeInfo: VolumeInfo,
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("authors")
    @Expose
    var authors: String,
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: ImageLinks,
    @SerializedName("averageRating")
    @Expose
    var rate: String,
    @SerializedName("publishedDate")
    @Expose
    var date: String,
    @SerializedName("pageCount")
    @Expose
    var pages: String,
    @SerializedName("previewLink")
    @Expose
    var link: String,
    @SerializedName("items")
    @Expose
    var books: ArrayList<Book>
) {
    
}