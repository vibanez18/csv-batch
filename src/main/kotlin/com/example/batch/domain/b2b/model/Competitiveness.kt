package com.example.batch.domain.b2b.model

enum class Competitiveness(val id: Int) {
    FAIR(1),
    NOT_COMPETITIVE(2),
    UNFAIR(3),
    DISCONNECTED(4),
    NOT_AVAILABLE(99);
}