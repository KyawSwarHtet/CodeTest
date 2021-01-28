package com.example.retrofitexercise1.ui.home.NowPlaying.Model

data class NowPlaying(
    val dates: DatesX,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)