package br.com.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.convidados.model.GuestModel
import br.com.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository(application)
    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    private val _saveGuest = MutableLiveData<String>()
    val saveGuest: LiveData<String> = _saveGuest


    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }

    fun save(guestModel: GuestModel) {
        if (guestModel.id == 0) {
            if (repository.insert(guestModel)) {
                _saveGuest.value = "Convidado inserido com sucesso!"
            } else {
                _saveGuest.value = "Existem campos invalidos"
            }
        } else {
            if (repository.update(guestModel)) {
                _saveGuest.value = "Convidado atualizado com sucesso!"
            } else {
                _saveGuest.value = "Existem campos invalidos"
            }
        }
    }
}
