package com.sintatsky.astest.domain.entity.search

data class Byline(
    val organization: Any,
    val original: String,
    val person: List<Person>
)