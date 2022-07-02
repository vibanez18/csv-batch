package com.example.batch.infrastructure.batch.processor

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.batch.item.ItemProcessor

class Processor: Logging {
    operator fun invoke(): ItemProcessor<String, String> = ItemProcessor {
        logger.info("${Thread.currentThread().name}:: Processing $it")
        it.uppercase()
    }
}