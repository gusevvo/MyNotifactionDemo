package com.example.mynotifactiondemo.common

interface MappingProfileInterface<in TSource, out TDestination> {
    fun map(source: TSource): TDestination
}