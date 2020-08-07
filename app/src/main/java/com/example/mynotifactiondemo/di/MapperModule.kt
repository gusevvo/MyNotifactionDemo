package com.example.mynotifactiondemo.di

import com.example.mynotifactiondemo.common.mapping.Mapper
import com.example.mynotifactiondemo.common.mapping.profiles.MyTransportationResponseDtoToMyTransportationDetailsModelMappingProfile
import com.example.mynotifactiondemo.common.mapping.profiles.MyTransportationsResponseItemDtoToMyTransportationListItemModelMappingProfile
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
            addMapping(MyTransportationResponseDtoToMyTransportationDetailsModelMappingProfile())
        }
    }
}