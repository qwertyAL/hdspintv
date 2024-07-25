package com.example.domain.model

data class MovieModel(
    val id: Long,
    val author: String,
    val date: String,
    val shortStory: String,
    val fullStory: String,
    val title: String,
    val description: String,
    val keywords: List<String>,
    val category: List<Int>,
    val altName: String,
    val commNum: Long,
    val allowComm: Int,
    val allowMain: Int,
    val approve: Int,
    val fixed: Int,
    val allowBr: Int,
    val symbol: String,
    val tags: List<String>,
    val knpId: Long,
    val metaTitle: String,
    val xFields: Map<String, String>
)