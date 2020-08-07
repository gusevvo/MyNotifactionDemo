package com.example.mynotifactiondemo.common.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.mynotifactiondemo.R


private const val NOTIFICATION_ID = 0
private const val NOTIFICATION_CHANEL_ID = "my_chanel_id"
private const val NOTIFICATION_CHANEL_NAME = "my_chanel"

fun NotificationManager.sendNotification(title: String,body: String, applicationContext: Context) {
    val builder = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANEL_ID)
        .setSmallIcon(R.drawable.ic_logo)
        .setContentTitle(title)
        .setContentText(body)
        .setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
    notify(NOTIFICATION_ID, builder.build())
}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}

fun NotificationManager.createChannel(channelId: String, channelName: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.enableVibration(true)
        notificationChannel.description = "Verification code"
        createNotificationChannel(notificationChannel)
    }
}
