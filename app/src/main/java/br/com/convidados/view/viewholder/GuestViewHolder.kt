package br.com.convidados.view.viewholder

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import br.com.convidados.databinding.RowGuestBinding
import br.com.convidados.model.GuestModel
import br.com.convidados.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {
    fun bind(guest: GuestModel) {
        bind.textName.text = "${guest.id} - ${guest.name}"
        bind.textName.setOnClickListener {
            listener.onClick(guest.id)
        }
        bind.buttonDelete.setOnClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de convidado")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton(
                    "Sim"
                ) { _, _ ->
                    listener.onDelete(guest.id)
                }
                .setNegativeButton("Não", null)
                .create()
                .show()
        }
    }
}