package com.teamx.raseef.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.teamx.raseef.constants.AppConstants
import com.teamx.raseef.data.local.ProductConverter
import com.teamx.raseef.data.local.TypeConverterMV
import com.teamx.raseef.data.local.UserConverter
import com.teamx.raseef.data.models.MusicModel


@Database(entities = [MusicModel::class, /*CartTable::class*/],
    version = 8,
    exportSchema = false
)

@TypeConverters(TypeConverterMV::class, ProductConverter::class, UserConverter::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun appDao(): AppDao
//    abstract fun CartDao(): CartDao


    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance
                ?: synchronized(this) { instance
                    ?: buildDatabase(
                        context
                    )
                        .also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, AppConstants.DbConfiguration.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

}