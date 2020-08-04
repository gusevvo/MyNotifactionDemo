package com.example.mynotifactiondemo.common

import java.lang.IllegalArgumentException
import kotlin.reflect.KClass


class Mapper {
    val profiles = mutableMapOf<Pair<KClass<*>, KClass<*>>, MappingProfileInterface<*, *>>()

    inline fun <reified TSource, reified TDestination> addMapping(mapping: MappingProfileInterface<TSource, TDestination>) {
        val key = Pair(TSource::class, TDestination::class)
        profiles[key] = mapping
    }

    @Throws(IllegalArgumentException::class)
    inline fun <reified TDestination> map(source: Any): TDestination {
        val key = Pair(source::class, TDestination::class)

        val mapping = profiles[key] as MappingProfileInterface<Any, TDestination>?
            ?: throw IllegalArgumentException("Not found mapping from `${source::class}` to `${TDestination::class}`")

        return mapping.map(source)
    }

}