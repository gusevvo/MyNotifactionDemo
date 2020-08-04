package com.example.mynotifactiondemo.di

import com.example.mynotifactiondemo.common.Mapper
import com.example.mynotifactiondemo.common.mappingprofiles.MyTransportationsResponseItemDtoToMyTransportationListItemModelMappingProfile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class MapperModule {

    @Provides
    @Singleton
    fun provideMapper(): Mapper {
        return Mapper().apply {
            addMapping(MyTransportationsResponseItemDtoToMyTransportationListItemModelMappingProfile())
        }
    }
}