package com.i.news.data.source.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.i.news.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(source: Source): String = "${source.id}, ${source.name}"

    @TypeConverter
    fun stringToSource(source: String): Source = source.split(", ").let { Source(id = it[0], name = it[1]) }
}