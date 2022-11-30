package com.teamx.raseef.data.local.dbModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teamx.raseef.data.models.productBySlug.ProductBySlugData

@Entity(tableName = "cart_table")
data class CartTable(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "product")
    val product: ProductBySlugData
)