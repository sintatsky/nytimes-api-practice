package com.sintatsky.astest.domain.entity.search

data class Response(
    val docs: List<Doc>,
    val meta: Meta
)