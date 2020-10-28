package com.backbase.assignment.ui.presentation.util

sealed class Failure {
    data class ServerFailure(val responseCode: Int): Failure()
    object ConnectionFailure : Failure()
}