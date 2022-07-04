package com.example.batch.infrastructure.batch.processor

import com.example.batch.domain.b2b.entity.B2bEntity
import com.example.batch.domain.b2b.entity.B2bEntityMapper
import com.example.batch.domain.b2b.model.ModelB2b
import org.apache.logging.log4j.kotlin.Logging
import org.springframework.batch.item.ItemProcessor
import org.springframework.stereotype.Component

@Component
class Processor(val b2bEntityMapper: B2bEntityMapper): Logging {
    operator fun invoke(): ItemProcessor<ModelB2b, B2bEntity> = ItemProcessor {
        b2bEntityMapper.fromModelB2b(it)
    }
}