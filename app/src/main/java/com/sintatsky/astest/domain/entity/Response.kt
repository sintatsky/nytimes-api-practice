package com.sintatsky.astest.domain.entity

data class Response(
    val hasMore: Boolean? = false,
    val num_results: Long,
    val results: List<ReviewResult>
)