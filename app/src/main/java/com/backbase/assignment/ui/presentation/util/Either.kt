package com.backbase.assignment.ui.presentation.util

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class Either<out T, out V> {
    @Parcelize data class Left<T: Parcelable, V: Parcelable>(val data: T): Either<T, V>(), Parcelable
    @Parcelize data class Right<T: Parcelable, V: Parcelable>(val data: V): Either<T, V>(), Parcelable
}