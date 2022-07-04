package com.example.batch.domain.b2b.entity

import com.google.gson.Gson

data class B2bEntity(
    val modelId: Long,
    val audience: String,
    val platform: String,
    val content: String
)