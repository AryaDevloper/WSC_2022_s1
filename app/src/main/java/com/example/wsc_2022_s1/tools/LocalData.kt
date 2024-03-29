package com.example.wsc_2022_s1.tools

import android.content.Context
import org.json.JSONArray
import java.io.File
import java.io.FileOutputStream

class LocalData(private val context: Context) {

    private val eventsFile = "events_data.json"

    fun eventsDataExists(): Boolean {
        val file = File(context.cacheDir, eventsFile)
        return file.exists()
    }

    fun createEventsCache() {
        val file = File(context.cacheDir, eventsFile)

        val inputStream = context.assets.open(eventsFile)

        inputStream.use { input ->
            FileOutputStream(file).use {
                input.copyTo(it)
            }
        }
    }

    fun getEvents(): JSONArray {
        val file = File(context.cacheDir, eventsFile)
        return JSONArray(file.readText())
    }

    fun setEvents(j: JSONArray) {
        val file = File(context.cacheDir, eventsFile)

        file.writeText(j.toString())
    }


    fun getOpeningTickets(): JSONArray {
        val file = File(context.cacheDir, "opening_tickets.json")
        if (!file.exists()) return JSONArray()

        return JSONArray(file.readText())
    }

    fun getClosingTickets(): JSONArray {
        val file = File(context.cacheDir, "closing_tickets.json")
        if (!file.exists()) return JSONArray()

        return JSONArray(file.readText())
    }

    fun setOpeningTickets(json: JSONArray) {
        val file = File(context.cacheDir, "opening_tickets.json")
        file.writeText(json.toString())
    }

    fun setClosingTickets(json: JSONArray) {
        val file = File(context.cacheDir, "closing_tickets.json")
        file.writeText(json.toString())
    }


}