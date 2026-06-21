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
            sb.append("═══════════════════════════════════\n")
            sb.append("الكلمة: ${word.kurdishWord}\n")
            sb.append("النطق: ${word.pronunciation}\n")
            sb.append("المعنى: ${word.meaning}\n")
            sb.append("الشرح: ${word.explanation}\n")
            sb.append("الأمثلة: ${word.examples}\n")
            sb.append("المرادفات: ${word.synonyms}\n")
            sb.append("الأضداد: ${word.antonyms}\n")
            sb.append("الملاحظات: ${word.notes}\n")
            sb.append("المصدر: ${word.source}\n")
            sb.append("الفئة: ${word.category}\n\n")
        }
        val fileName = "sorani_backup_${dateFormat.format(Date())}.txt"
        val file = File(context.getExternalFilesDir(null), fileName)
        file.writeText(sb.toString())
        return file.absolutePath
    }
}
