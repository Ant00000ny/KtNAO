package com.isekaiofficial.ktnao

import com.isekaiofficial.ktnao.enums.SaucenaoDbEnum

class DbIndex {
    private val indices = SaucenaoDbEnum
        .entries
        .associateWith { false }
        .toMutableMap()

    fun enable(vararg index: SaucenaoDbEnum): DbIndex {
        index.forEach {
            indices[it] = true
        }

        return this
    }

    fun disable(vararg index: SaucenaoDbEnum): DbIndex {
        index.forEach {
            indices[it] = false
        }

        return this
    }

    fun enableAll(): DbIndex {
        indices.replaceAll { _, _ -> true }
        return this
    }

    fun getValue(): Long {
        return indices
            .values
            .joinToString("") { if (it) "1" else "0" }
            .reversed()
            .toLong(2)
    }
}
