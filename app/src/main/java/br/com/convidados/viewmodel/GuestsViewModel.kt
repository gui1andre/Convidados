package br.com.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.convidados.model.GuestModel
import br.com.convidados.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository(application.applicationContext)
    private val listAllGuest = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuest

    fun getAll() {
        listAllGuest.value =  repository.getAll()
    }

    fun delete(id: Int) {
        repository.delete(id)
    }

    fun getAbsent(){
        listAllGuest.value = repository.getAbsent()
    }

    fun getPresent(){
        listAllGuest.value = repository.getPresent()
    }
}