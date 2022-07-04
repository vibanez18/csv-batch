package com.example.batch.domain.b2b.model

data class ModelB2b(
    val modelId: Long,
    val mmId: Long?,
    val market: Market,
    val platform: Platform,
    val audience: Audience,
    val segment: SegmentB2b,
    val competitiveness: ModelCompetitiveness,
    val quality: ModelQuality,
    val description: String = "",
    val headScore: Int = 0
)