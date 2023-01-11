package com.teamx.raseef.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.provider.ContactsContract.Data
import com.teamx.raseef.constants.AppConstants

class PrefHelper private constructor() {

    companion object {
        private val sharePref = PrefHelper()
        private lateinit var sharedPreferences: SharedPreferences

        private val PLACE_OBJ = "place_obj"
        private val PAYTYPE = "pay_type"

        fun getInstance(context: Context): PrefHelper {
            if (!::sharedPreferences.isInitialized) {
                synchronized(PrefHelper::class.java) {
                    if (!::sharedPreferences.isInitialized) {
                        sharedPreferences =
                            context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
                    }
                }
            }
            return sharePref
        }
    }

    val paymentMathod: String? get() = sharedPreferences.getString(PAYTYPE, "0")


    fun savePaymentMethod(pay_type: String) {
        sharedPreferences.edit().putString(PAYTYPE, pay_type).apply()
    }

    val payment: Int get() = sharedPreferences.getInt(PLACE_OBJ, 1)

    val name : String? get() = sharedPreferences.getString(AppConstants.DataStore.NAME, "")
    val email : String? get() = sharedPreferences.getString(AppConstants.DataStore.EMAIL, "")
    val avatar : String? get() = sharedPreferences.getString(AppConstants.DataStore.AVATAR, "")
    val number : String? get() = sharedPreferences.getString(AppConstants.DataStore.NUMBER, "")

    fun saveProfile(firstname: String, email: String, avatar: String, number: String) {
        sharedPreferences.edit().putString(AppConstants.DataStore.NAME, firstname).apply()
        sharedPreferences.edit().putString(AppConstants.DataStore.EMAIL, email).apply()
        sharedPreferences.edit().putString(AppConstants.DataStore.AVATAR, avatar).apply()
        sharedPreferences.edit().putString(AppConstants.DataStore.NUMBER, number).apply()
    }

    fun savePayment(placeObjStr: Int) {
        sharedPreferences.edit().putInt(PLACE_OBJ, placeObjStr).apply()
    }

    fun removePlaceObj() {
        sharedPreferences.edit().remove(PLACE_OBJ).apply()
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

}