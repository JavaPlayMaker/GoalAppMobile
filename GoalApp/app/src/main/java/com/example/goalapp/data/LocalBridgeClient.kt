package com.example.goalapp.data

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object LocalBridgeClient {

    private const val PC_IP = ""
    private const val BASE_URL = "http://$PC_IP:8080"

    val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun saveJournalEntry(entry: JournalEntry) {
        httpClient.post("$BASE_URL/journal") {
            contentType(ContentType.Application.Json)
            setBody(entry)
        }
    }
}
