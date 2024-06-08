package com.i.news.data.source.remote.dto


import com.google.gson.annotations.SerializedName
import com.i.news.domain.model.Source

data class SourceDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)

fun SourceDto.toSource() = Source(id = id, name = name)