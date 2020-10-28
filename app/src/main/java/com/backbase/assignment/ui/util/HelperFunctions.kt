package com.backbase.assignment.ui.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import com.backbase.assignment.ui.util.IMG_BASE_URL
import com.backbase.assignment.ui.util.MINIMUM_FILE_SIZE
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun generateImageUrl(filePath: String, fileSize: Int = MINIMUM_FILE_SIZE) = IMG_BASE_URL + "w${fileSize}/${filePath}"

fun Application.startNetworkCallback(){
    val cm: ConnectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val builder: NetworkRequest.Builder = NetworkRequest.Builder()

    cm.registerNetworkCallback(
        builder.build(),
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                isNetworkActive = true
            }
            override fun onLost(network: Network) {
                isNetworkActive = false
            }
        })
}


fun getLongformDate(date: String): String {
    val formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy")
    return LocalDate.parse(date).format(formatter)
}

fun getHourAndMinuteFromMinute(minute: Int): String {
    val hour = minute / 60
    val hrString = if(hour > 0) "${hour}h" else ""
    val min = minute % 60
    val minString = if(min > 0) "$min" else ""
    return "${hrString} ${min}"
}