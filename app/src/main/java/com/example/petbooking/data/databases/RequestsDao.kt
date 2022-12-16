package com.example.petbooking.data.databases
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RequestsDao {
    @Query("SELECT * FROM REQUESTS_TABLE WHERE status = 1 OR status = 2")
    abstract fun getActiveRequests(): Flow<List<RequestsEntity>>

    @Query("SELECT * FROM REQUESTS_TABLE WHERE status = 0")
    abstract fun getCancelRequests(): Flow<List<RequestsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entities: List<RequestsEntity>)

    @Query("DELETE FROM REQUESTS_TABLE")
    abstract suspend fun deleteAll()

    @Transaction
    open suspend fun updateRequests(requests: List<RequestsEntity>) {
        deleteAll()
        insert(requests)
    }
}