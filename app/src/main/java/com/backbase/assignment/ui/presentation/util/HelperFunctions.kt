package com.backbase.assignment.ui.presentation.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import java.text.SimpleDateFormat

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
    val inputFormat = SimpleDateFormat("yyyy-MM-dd")
    val outputFormat = SimpleDateFormat("MMMMM dd, yyyy")
    return outputFormat.format(inputFormat.parse(date))
}

fun getHourAndMinuteFromMinute(minute: Int): String {
    val hour = minute / 60
    val hrString = if(hour > 0) "${hour}h" else ""
    val min = minute % 60
    val minString = if(min > 0) { if(hrString.isNotBlank()) " ${min}m" else "${min}m" } else ""
    return "${hrString}${minString}"
}