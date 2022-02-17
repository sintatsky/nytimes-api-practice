package com.sintatsky.astest.domain.entity

data class Response(
    val num_results: Int,
    val results: List<ReviewResult>
)