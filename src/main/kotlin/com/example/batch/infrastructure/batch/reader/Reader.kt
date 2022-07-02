package com.example.batch.infrastructure.batch.reader

import org.springframework.batch.item.support.ListItemReader

class Reader {
    operator fun invoke() = ListItemReader((0..100).map { i -> i.toString() })
}