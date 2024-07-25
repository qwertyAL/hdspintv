package com.example.data.response

import com.example.domain.model.MovieModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val id: Long,
    @SerialName("autor") val author: String,
    val date: String,
    @SerialName("short_story") val shortStory: String,
    @SerialName("full_story") val fullStory: String,
    val title: String,
    @SerialName("descr") val description: String,
    val keywords: String,
    val category: String,
    @SerialName("alt_name") val altName: String,
    @SerialName("comm_num") val commNum: Long,
    @SerialName("allow_comm") val allowComm: Int,
    @SerialName("allow_main") val allowMain: Int,
    val approve: Int,
    val fixed: Int,
    @SerialName("allow_br") val allowBr: Int,
    val symbol: String,
    val tags: String,
    @SerialName("kinopoisk_id") val knpId: Long,
    @SerialName("metatitle") val metaTitle: String,
    @SerialName("xfields") val xFields: String
)

fun MovieResponse.toMovieModel() = MovieModel(
    id = id,
    author = author.trim(),
    date = date.trim(),
    shortStory = shortStory.trim(),
    fullStory = fullStory.trim(),
    title = title.trim(),
    description = description.trim(),
    keywords = keywords.split(",").map { it.trim() },
    category = category.split(",").map { it.trim().toInt() },
    altName = altName.trim(),
    commNum = commNum,
    allowComm = allowComm,
    allowMain = allowMain,
    approve = approve,
    fixed = fixed,
    allowBr = allowBr,
    symbol = symbol.trim(),
    tags = tags.split(",").map { it.trim() },
    knpId = knpId,
    metaTitle = metaTitle,
    xFields = xFields.split("||").associate { it.substringBefore("|") to it.substringAfter("|") }
)