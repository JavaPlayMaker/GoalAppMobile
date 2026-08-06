package com.example.goalapp.data

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object LocalBridgeClient {

    private const val TAG = "LocalBridgeClient"
    private const val PC_IP = "192.222" // Set locally for dev
    private const val BASE_URL = "http://$PC_IP:8080"

    val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }

    /**
     * Saves a journal entry to the server.
     * Returns a Result indicating success or failure.
     */
    suspend fun saveJournalEntry(entry: JournalEntry): Result<Unit> {
        return try {
            val response = httpClient.post("$BASE_URL/journal") {
                contentType(ContentType.Application.Json)
                setBody(entry)
            }
            if (response.status.isSuccess()) {
                Result.success(Unit)
            } else {
                val errorMsg = "Server returned error: ${response.status}"
                Log.e(TAG, errorMsg)
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Log.e(TAG, "Exception during saveJournalEntry: ${e.message}", e)
            Result.failure(e)
        }
    }

    /**
     * Retrieves a journal entry for a specific user and date.
     */
    suspend fun getJournalEntry(userId: String, date: String): String? {
        return try {
            val response = httpClient.get("$BASE_URL/journal") {
                parameter("userId", userId)
                parameter("date", date)
            }
            if (response.status.isSuccess()) {
                val entry: JournalEntry = response.body()
                entry.content
            } else {
                Log.w(TAG, "Get journal returned status: ${response.status}")
                null
            }
        } catch (e: Exception) {
            Log.e(TAG, "Exception during getJournalEntry: ${e.message}", e)
            null
        }
    }
}
