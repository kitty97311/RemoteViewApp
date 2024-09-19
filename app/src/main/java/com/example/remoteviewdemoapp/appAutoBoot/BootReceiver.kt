package com.example.remoteviewdemoapp.appAutoBoot

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.remoteviewdemoapp.MyForegroundService

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val serviceIntent = Intent(context, MyForegroundService::class.java)
            context.startForegroundService(serviceIntent)

        }
    }
}