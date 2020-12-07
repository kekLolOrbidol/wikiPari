package me.navid.scrambilo.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent

class NotificationMessage {
    fun scheduleNotification(context: Context) {
        configMsg(context, 0)
    }

    private fun configMsg( context: Context, type: Int) {
        val i = Intent(context, MyBroacastReceiver::class.java)
        i.putExtra(TYPE_EXTRA, type)
        val pendingIntent = PendingIntent.getBroadcast(context, type, i, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManagerRTC = context.getSystemService(ALARM_SERVICE) as AlarmManager
        val interval = 1000 * 60 * 60 * 8
        alarmManagerRTC.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval,
            interval.toLong(), pendingIntent)
    }

    fun getNotificationManager(context: Context): Any? {
        return context.getSystemService(Context.NOTIFICATION_SERVICE)
    }

    companion object {
        const val TYPE_EXTRA = "type"
    }
}