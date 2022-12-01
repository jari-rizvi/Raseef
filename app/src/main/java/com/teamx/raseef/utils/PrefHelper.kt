package com.teamx.raseef.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class PrefHelper private constructor() {

    companion object {
        private val sharePref = PrefHelper()
        private lateinit var sharedPreferences: SharedPreferences

        private val PLACE_OBJ = "place_obj"

        fun getInstance(context: Context): PrefHelper {
            if (!::sharedPreferences.isInitialized) {
                synchronized(PrefHelper::class.java) {
                    if (!::sharedPreferences.isInitialized) {
                        sharedPreferences = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
                    }
                }
            }
            return sharePref
        }
    }

    val payment: Int
        get() = sharedPreferences.getInt(PLACE_OBJ, 1)



    fun savePayment(placeObjStr: Int) {
        sharedPreferences.edit()
            .putInt(PLACE_OBJ, placeObjStr)
            .apply()
    }

    fun removePlaceObj() {
        sharedPreferences.edit().remove(PLACE_OBJ).apply()
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

}