package com.backbase.assignment.ui.util

sealed class Failure {
    class ServerFailure(val responseCode: Int): Failure()
    object ConnectionFailure : Failure()
}