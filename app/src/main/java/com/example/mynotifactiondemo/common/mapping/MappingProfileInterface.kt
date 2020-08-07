package com.example.mynotifactiondemo.common.mapping

interface MappingProfileInterface<in TSource, out TDestination> {
    fun map(source: TSource): TDestination
}