package com.backbase.assignment.ui.presentation.util

sealed class Failure {
    class ServerFailure(val responseCode: Int): Failure()
    object ConnectionFailure : Failure()
}