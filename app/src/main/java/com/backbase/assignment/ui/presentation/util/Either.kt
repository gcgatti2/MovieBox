package com.backbase.assignment.ui.presentation.util

sealed class Either<T, V> {
    data class Left<T, V>(val data: T): Either<T, V>()
    data class Right<T, V>(val data: V): Either<T, V>()
}

fun <T, V> Success(v: V): Either.Right<T, V> = Either.Right(v)
fun <T, V> Failure(e: T): Either.Left<T, V> = Either.Left(e)
