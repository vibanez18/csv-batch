package com.example.batch.domain.b2b.model

data class ModelCompetitiveness(
    val type: Competitiveness,
    val range: Int?,
    val isDeliveryTimeCompetitive: Boolean,
)
