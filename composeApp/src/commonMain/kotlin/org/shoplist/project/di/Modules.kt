package org.shoplist.project.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.shoplist.project.core.data.HttpClientFactory
import org.shoplist.project.shopList.data.network.DataSource
import org.shoplist.project.shopList.data.network.DataSourceImp
import org.shoplist.project.shopList.data.repository.MainRepositoryImp
import org.shoplist.project.shopList.domain.repository.MainRepository
import org.shoplist.project.shopList.presentation.ShoppingViewModel

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::DataSourceImp).bind<DataSource>()
    singleOf(::MainRepositoryImp).bind<MainRepository>()
    viewModelOf(::ShoppingViewModel)
}