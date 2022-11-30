package com.teamx.raseef.data.local

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.teamx.raseef.data.models.productBySlug.ProductBySlugData
import com.teamx.raseef.dataclasses.login.LoginData
import java.io.ByteArrayOutputStream

class TypeConverterMV {
    @TypeConverter
    fun toByteArray(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        val asdf = outputStream.toByteArray()
        return asdf
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap {
        var bitmap: Bitmap? = null
        bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        return bitmap
    }
}

class ProductConverter {
    @TypeConverter
    fun stringToProduct(string: String?): ProductBySlugData? {
        val gson = Gson()
        return gson.fromJson(string, ProductBySlugData::class.java)
    }

    @TypeConverter
    fun productToString(productById: ProductBySlugData?): String? {
        val gson = Gson()
        return gson.toJson(productById)
    }
}

class UserConverter {
    @TypeConverter
    fun stringToUser(string: String?): LoginData? {
        val gson = Gson()
        return gson.fromJson(string, LoginData::class.java)
    }

    @TypeConverter
    fun userToString(productById: LoginData?): String? {
        val gson = Gson()
        return gson.toJson(productById)
    }
}

