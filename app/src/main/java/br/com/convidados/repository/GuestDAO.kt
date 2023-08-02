package br.com.convidados.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.convidados.constants.DataBaseConstants
import br.com.convidados.model.GuestModel

@Dao
interface GuestDAO {

    @Insert
    fun insert(guest: GuestModel): Long

    @Update
    fun update(guest: GuestModel): Int

    @Delete
    fun delete(guest: GuestModel)

    @Query("SELECT * FROM ${DataBaseConstants.GUEST.TABLE_NAME} " +
            "WHERE ${DataBaseConstants.GUEST.COLUMNS.ID} = :id")
    fun get(id: Int): GuestModel

    @Query("SELECT * FROM ${DataBaseConstants.GUEST.TABLE_NAME}")
    fun getAll(): List<GuestModel>

    @Query("SELECT * FROM ${DataBaseConstants.GUEST.TABLE_NAME} " +
            "WHERE ${DataBaseConstants.GUEST.COLUMNS.PRESENCE} = 1")
    fun getPresent(): List<GuestModel>

    @Query("SELECT * FROM ${DataBaseConstants.GUEST.TABLE_NAME} " +
            "WHERE ${DataBaseConstants.GUEST.COLUMNS.PRESENCE} = 0")
    fun getAbsent(): List<GuestModel>

}