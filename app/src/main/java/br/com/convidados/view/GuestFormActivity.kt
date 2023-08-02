package br.com.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.convidados.R
import br.com.convidados.constants.DataBaseConstants
import br.com.convidados.databinding.ActivityGuestFormBinding
import br.com.convidados.model.GuestModel
import br.com.convidados.viewmodel.GuestFormViewModel


class GuestFormActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    private var guestId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]

        binding.buttonSave.setOnClickListener(this)
        binding.radioPresent.isChecked = true

        observe()
        loadData()
    }

    private fun observe() {
        viewModel.guest.observe(this) {
            binding.editName.setText(it.name)
            if (!it.presence) {
                binding.radioPresent.isChecked = false
                binding.radioAbsent.isChecked = true
            }
        }

        viewModel.saveGuest.observe(this){
            if(it != ""){
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked
            val guest = GuestModel().apply {
                this.id = 0
                this.name = name
                this.presence = presence

            }
            viewModel.save(guest)
        }
    }
}