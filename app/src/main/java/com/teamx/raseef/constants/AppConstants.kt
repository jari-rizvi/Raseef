package com.teamx.raseef.constants

import androidx.annotation.StringDef


object AppConstants {

    @StringDef(ApiConfiguration.BASE_URL)
    annotation class ApiConfiguration {
        companion object {
            const val BASE_URL = "https://api.teamxmv.com/api/"
        }
    }

    @StringDef(DbConfiguration.DB_NAME)
    annotation class DbConfiguration {
        companion object {
            const val DB_NAME = "BaseProject"
        }
    }


    @StringDef(DataStore.DATA_STORE_NAME,
        DataStore.LOCALIZATION_KEY_NAME,
        DataStore.USER_NAME_KEY,
        DataStore.TOKEN,
        DataStore.DETAILS,
        DataStore.PAYMENT,
        DataStore.SAVE_ID,
        DataStore.AVATAR,
        DataStore.NUMBER,
        DataStore.NAME)
    annotation class DataStore {
        companion object {
            const val DATA_STORE_NAME = "BaseProject"
            const val LOCALIZATION_KEY_NAME = "lang"
            const val USER_NAME_KEY = "user_name_key"
            const val TOKEN = "token"
            const val DETAILS = "details"
            const val PAYMENT = "payment"
            const val SAVE_ID = "save_id"
            const val AVATAR = "avatar"
            const val NUMBER = "number"
            const val NAME = "name"
        }
    }

}