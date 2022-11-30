package com.teamx.raseef.data.local.dbModel

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//@Dao
//interface CartDao {
//
//    @Query("SELECT * FROM cart_table")
//    fun getAllProducts(): LiveData<List<CartTable>>
//
//    @Query("SELECT * FROM cart_table")
//    fun getAllProducts2(): List<CartTable>
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(product: CartTable)
//
//    @Query("DELETE FROM cart_table")
//    suspend fun deleteAllCart()
//
//    @Query("DELETE FROM cart_table WHERE id = :userId")
//    suspend fun deleteByProductId(userId: Int)
//}