package br.com.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.convidados.constants.DataBaseConstants
import br.com.convidados.databinding.FragmentAbsentBinding
import br.com.convidados.databinding.FragmentAllGuestsBinding
import br.com.convidados.view.adapter.GuestsAdapter
import br.com.convidados.view.listener.OnGuestListener
import br.com.convidados.viewmodel.GuestsViewModel

class AbsentFragment : Fragment() {

    private var _binding: FragmentAbsentBinding? = null
    private lateinit var viewModel: GuestsViewModel
    private val adapter = GuestsAdapter()
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel =
            ViewModelProvider(this)[GuestsViewModel::class.java]

        _binding = FragmentAbsentBinding.inflate(inflater, container, false)
        binding.recyclerGuests.layoutManager = LinearLayoutManager(context)
        binding.recyclerGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {

                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getAbsent()
            }
        }
        adapter.attachListener(listener)
        observer()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAbsent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observer() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updateGuests(it)
        }
    }
}