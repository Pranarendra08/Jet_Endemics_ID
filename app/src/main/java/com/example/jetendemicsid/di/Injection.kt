package com.example.jetendemicsid.di

import com.example.jetendemicsid.data.HewanRepository

object Injection {
    fun provideRepository(): HewanRepository {
        return HewanRepository.getInstance()
    }
}