package com.example.goalapp.data

import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object SupabaseRepository {
    private val client = SupabaseManager.client

    // --- Activities ---

    suspend fun getActivities(): List<GoalActivity> = withContext(Dispatchers.IO) {
        client.postgrest["activities"].select().decodeList<GoalActivity>()
    }

    suspend fun insertInitialActivities(activities: List<GoalActivity>) = withContext(Dispatchers.IO) {
        client.postgrest["activities"].insert(activities)
    }

    // --- Journal ---

    suspend fun getJournalEntries(customerId: String): List<JournalEntry> = withContext(Dispatchers.IO) {
        client.postgrest["journal_entries"].select {
            filter {
                eq("customer_id", customerId)
            }
            order("created_at", Order.DESCENDING)
        }.decodeList<JournalEntry>()
    }

    suspend fun saveJournalEntry(entry: JournalEntry) = withContext(Dispatchers.IO) {
        client.postgrest["journal_entries"].insert(entry)
    }

    // --- Customer / Stats ---

    suspend fun getCustomer(email: String): Customer? = withContext(Dispatchers.IO) {
        client.postgrest["customers"].select {
            filter {
                eq("email", email)
            }
        }.decodeSingleOrNull<Customer>()
    }

    suspend fun createCustomer(customer: Customer) = withContext(Dispatchers.IO) {
        client.postgrest["customers"].insert(customer)
    }

    suspend fun getUserStats(customerId: String): UserStats? = withContext(Dispatchers.IO) {
        client.postgrest["user_stats"].select {
            filter {
                eq("customer_id", customerId)
            }
        }.decodeSingleOrNull<UserStats>()
    }

    suspend fun updateUserStats(stats: UserStats) = withContext(Dispatchers.IO) {
        client.postgrest["user_stats"].upsert(stats)
    }
}
