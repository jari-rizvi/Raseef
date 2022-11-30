package com.teamx.raseef.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teamx.raseef.data.models.MusicModel

@Dao
interface AppDao {
        @Query("SELECT * FROM music_table")
    fun getAllProducts(): LiveData<List<MusicModel>>

    @Query("SELECT * FROM music_table")
    fun getAllProducts2(): List<MusicModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: MusicModel)

    @Query("DELETE FROM music_table")
    suspend fun deleteAllCart()

    @Query("DELETE FROM music_table WHERE id = :userId")
    suspend fun deleteByProductId(userId: Int)


}