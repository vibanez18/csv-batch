package com.example.batch.domain.b2b.entity

import com.example.batch.domain.b2b.model.ModelB2b
import com.google.gson.Gson
import org.springframework.stereotype.Component

@Component
class B2bEntityMapper {
    fun fromModelB2b(modelB2b: ModelB2b) = B2bEntity(
        modelId = modelB2b.modelId,
        audience = modelB2b.audience.name,
        platform = modelB2b.platform.name,
        content = Gson().toJson(modelB2b)
    )
}