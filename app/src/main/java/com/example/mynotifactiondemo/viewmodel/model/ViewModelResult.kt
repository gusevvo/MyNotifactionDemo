package com.example.mynotifactiondemo.viewmodel.model

/**
 * inspired by:
 *   kotlin/util/Result.kt
 *   https://github.com/MindorksOpenSource/Dagger-Hilt-Tutorial/blob/master/app/src/main/java/com/mindorks/framework/mvvm/utils/Resource.kt
 *   https://github.com/android/architecture-samples/blob/master/app/src/main/java/com/example/android/architecture/blueprints/todoapp/data/Result.kt
 */
data class ViewModelResult<out T> internal constructor(
    val value: Any?
) {

    enum class Status {
        SUCCESS,
        FAILURE,
        LOADING
    }

    val status: Status
        get() {
            return when (value) {
                is Loading -> Status.LOADING
                is Failure -> Status.FAILURE
                else -> Status.SUCCESS
            }
        }

    fun getValueOrNull(): T? =
        when (status) {
            Status.SUCCESS -> value as T
            else -> null
        }

    fun getFailureOrNull(): Failure? =
        when (status) {
            Status.FAILURE -> value as Failure
            else -> null
        }

    companion object {

        fun <T> success(value: T?): ViewModelResult<T> { Result
            return ViewModelResult(value)
        }

        fun <T> failure(throwable: Throwable): ViewModelResult<T> {
            return ViewModelResult(Failure(throwable))
        }

        fun <T> loading(): ViewModelResult<T> {
            return ViewModelResult(Loading)
        }

    }

    data class Failure(val throwable: Throwable)
    internal object Loading
}