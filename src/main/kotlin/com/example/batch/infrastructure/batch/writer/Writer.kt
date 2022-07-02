package com.example.batch.infrastructure.batch.writer

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.batch.item.ItemWriter

class Writer:Logging {
    operator fun invoke(): ItemWriter<String> = ItemWriter {
        it.forEach { item -> logger.info("${Thread.currentThread().name}:: Writing $item") }
    }
}