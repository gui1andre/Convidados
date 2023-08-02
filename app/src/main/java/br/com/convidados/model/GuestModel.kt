package br.com.convidados.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.convidados.constants.DataBaseConstants

@Entity(tableName = DataBaseConstants.GUEST.TABLE_NAME)
class GuestModel{

    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo
    var name: String = ""

    @ColumnInfo
    var presence: Boolean = false
}