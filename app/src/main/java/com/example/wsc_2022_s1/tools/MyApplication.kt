package com.example.wsc_2022_s1.tools

import android.app.Application
import android.content.Intent
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.wsc_2022_s1.MainActivity2
import com.example.wsc_2022_s1.R
import java.io.File
import java.io.FileOutputStream

class MyApplication : Application() {

    private val data: LocalData by lazy {
        LocalData(this)
    }

    private fun createShortcuts() {
        val eventsShortCut = ShortcutInfoCompat.Builder(this, "events").apply {
            setShortLabel("Events")
            setLongLabel("Events")
            setIcon(IconCompat.createWithResource(this@MyApplication, R.drawable.group_485))
            setIntent(Intent(this@MyApplication, MainActivity2::class.java).apply {
                action = Intent.ACTION_VIEW
                putExtra("target", MainActivity2.EVENTS)
            })

        }.build()

        val ticketsShortcut = ShortcutInfoCompat.Builder(this, "tickets_shortcut").apply {
            setShortLabel("Tickets")
            setLongLabel("Tickets")
            setIcon(IconCompat.createWithResource(this@MyApplication, R.drawable.group_485))
            setIntent(Intent(this@MyApplication, MainActivity2::class.java).apply {
                action = Intent.ACTION_VIEW
                putExtra("target", MainActivity2.TICKETS)
            })
        }.build()


        val recordsShortcut = ShortcutInfoCompat.Builder(this, "records_shortcut").apply {
            setShortLabel("Records")
            setLongLabel("Records")
            setIcon(IconCompat.createWithResource(this@MyApplication, R.drawable.group_485))
            setIntent(Intent(this@MyApplication, MainActivity2::class.java).apply {
                putExtra("target", MainActivity2.RECORDS)
                action = Intent.ACTION_VIEW
            })
        }.build()


        ShortcutManagerCompat.pushDynamicShortcut(this, ticketsShortcut)
        ShortcutManagerCompat.pushDynamicShortcut(this, eventsShortCut)
        ShortcutManagerCompat.pushDynamicShortcut(this, recordsShortcut)
    }

    override fun onCreate() {
        super.onCreate()

        createShortcuts()

        if (!data.eventsDataExists()) {
            data.createEventsCache()
        }
    }

}