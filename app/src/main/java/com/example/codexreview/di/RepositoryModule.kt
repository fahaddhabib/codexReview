package com.example.codexreview.di

import com.example.codexreview.data.repository.UsersRepository
import com.example.codexreview.data.repository.UsersRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<UsersRepository> { UsersRepositoryImpl(get()) }
}
