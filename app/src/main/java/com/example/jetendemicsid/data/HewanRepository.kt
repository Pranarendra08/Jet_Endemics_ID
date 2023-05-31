package com.example.jetendemicsid.data

import com.example.jetendemicsid.model.DataHewan
import com.example.jetendemicsid.model.Hewan
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class HewanRepository {
    private val animal = mutableListOf<Hewan>()

    init {
        if (animal.isEmpty()) {
            DataHewan.hewan.forEach {
                animal.add(it)
            }
        }
    }

    fun getAllHewan(): Flow<List<Hewan>> {
        return flowOf(animal)
    }

    fun getHewanById(HewanId: Long): Hewan {
        return animal.first {
            it.id == HewanId
        }
    }

    fun searchHewan(query: String): List<Hewan>{
        return DataHewan.hewan.filter {
            it.nama.contains(query, ignoreCase = true)
        }
    }

    companion object {
        @Volatile
        private var instance: HewanRepository? = null

        fun getInstance(): HewanRepository =
            instance ?: synchronized(this) {
                HewanRepository().apply {
                    instance = this
                }
            }
    }
}