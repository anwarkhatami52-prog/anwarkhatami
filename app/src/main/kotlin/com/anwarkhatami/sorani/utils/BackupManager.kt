package com.anwarkhatami.sorani.utils

import android.content.Context
import com.google.gson.Gson
import com.anwarkhatami.sorani.data.local.entity.WordEntity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class BackupManager(private val context: Context) {
    private val gson = Gson()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US)

    fun exportToJson(words: List<WordEntity>): String {
        val json = gson.toJson(words)
        val fileName = "sorani_backup_${dateFormat.format(Date())}.json"
        val file = File(context.getExternalFilesDir(null), fileName)
        file.writeText(json)
        return file.absolutePath
    }

    fun importFromJson(filePath: String): List<WordEntity>? {
        return try {
            val file = File(filePath)
            val json = file.readText()
            val type = object : com.google.gson.reflect.TypeToken<List<WordEntity>>() {}.type
            gson.fromJson(json, type)
        } catch (e: Exception) {
            null
        }
    }

    fun exportToTxt(words: List<WordEntity>): String {
        val sb = StringBuilder()
        words.forEach { word ->
            sb.append("=".repeat(50) + "\n")
            sb.append("وشە: ${word.kurdishWord}\n")
            sb.append("لێپاڵاو: ${word.pronunciation}\n")
            sb.append("واتە: ${word.meaning}\n")
            sb.append("تێگەیاندنە: ${word.explanation}\n")
            sb.append("نموونە: ${word.examples}\n")
            sb.append("هاوواتە: ${word.synonyms}\n")
            sb.append("بەرانبەر: ${word.antonyms}\n")
            sb.append("تێبینی: ${word.notes}\n")
            sb.append("سەرچاوە: ${word.source}\n\n")
        }
        val fileName = "sorani_backup_${dateFormat.format(Date())}.txt"
        val file = File(context.getExternalFilesDir(null), fileName)
        file.writeText(sb.toString())
        return file.absolutePath
    }
}
