package br.com.convidados.repository

import android.annotation.SuppressLint
import android.content.Context
import br.com.convidados.model.GuestModel

class GuestRepository (context: Context) {

    private val guestDataBase = GuestDataBase.getDataBase(context).getDAO()

    fun insert(guest: GuestModel): Boolean {
        return guestDataBase.insert(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return guestDataBase.update(guest) > 0
    }

    fun delete(guestId: Int) {
        val guest = get(guestId)
        guestDataBase.delete(guest)
    }

    @SuppressLint("Range")
    fun get(id: Int): GuestModel {
        return guestDataBase.get(id)
    }

    @SuppressLint("Range")
    fun getAll(): List<GuestModel> {
        return guestDataBase.getAll()
    }

    @SuppressLint("Range")
    fun getPresent(): List<GuestModel> {
        return guestDataBase.getPresent()
    }

    @SuppressLint("Range")
    fun getAbsent(): List<GuestModel> {
        return guestDataBase.getAbsent()
    }


}