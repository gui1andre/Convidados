package br.com.convidados.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.convidados.model.GuestModel

@Database(entities = [GuestModel::class], version = 1, exportSchema = false)
abstract class GuestDataBase : RoomDatabase() {

    abstract fun getDAO(): GuestDAO

    companion object {
        private lateinit var INSTANCE: GuestDataBase
        fun getDataBase(context: Context): GuestDataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(GuestDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "guestdb")
                        .addMigrations()
                        .allowMainThreadQueries()
                        .build()
                    return INSTANCE
                }
            }
            return INSTANCE
        }
        private val MIGRATION_1_2: Migration = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {

            }

        }

    }


}