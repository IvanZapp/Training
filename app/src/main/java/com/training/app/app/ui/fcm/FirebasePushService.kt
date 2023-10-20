package com.training.app.app.ui.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.training.app.R
import com.training.app.app.ui.Navigator
import com.training.app.data.repository.PreferencesRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class FirebasePushService : FirebaseMessagingService() {

    private val preferencesRepository: PreferencesRepository by inject()
    private val navigator: Navigator by inject()

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        GlobalScope.launch {
            preferencesRepository.setPushToken(p0)
        }
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        handleNotification(p0)
    }

    private fun handleNotification(rm: RemoteMessage) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(getString(R.string.channel_notification_default), "default", importance)
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        val mBuilder = NotificationCompat.Builder(this, "default")
            .setSmallIcon(R.mipmap.ic_stat) // notification icon
            .setContentTitle(rm.notification?.title ?: getString(R.string.app_name)) // title for notification
            .setContentText(rm.notification?.body ?: "") // message for notification
            .setAutoCancel(true) // clear notification after click

        val pi = PendingIntent.getActivity(
            this,
            0,
            navigator.navigateToMain(),
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        mBuilder.setContentIntent(pi)
        val mNotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(0, mBuilder.build())
    }
}
