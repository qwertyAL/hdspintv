package com.example.tvpresentation.screen

enum class Screens(
    private val args: List<String>? = null,
) {

    Main,
    Auth,
    MovieDetails,
    VideoPlayer;

    operator fun invoke(): String {
        val argList = StringBuilder()
        args?.let { nnArgs ->
            nnArgs.forEach { arg -> argList.append("/{$arg}") }
        }
        return name + argList
    }

}