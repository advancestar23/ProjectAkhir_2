package com.vinz.projectcontoh1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vinz.projectcontoh1.R
import com.vinz.projectcontoh1.data.room.AppEntity

class FoodAdapter(private var playerList: List<AppEntity>) :
    RecyclerView.Adapter<FoodAdapter.PlayerViewHolder>() {

    // Deklarasi variabel untuk callback ketika item diklik
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Fungsi untuk mengatur callback ketika item diklik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface untuk callback ketika item diklik
    interface OnItemClickCallback {
        fun onItemClicked(data: AppEntity)
        fun onDelete(data: AppEntity, position: Int)
        fun onUpdate(data: AppEntity)
    }

    // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodName: TextView = itemView.findViewById(R.id.food_name)
        val foodCategory: TextView = itemView.findViewById(R.id.food_category)
        val btnEdit: ImageView = itemView.findViewById(R.id.btn_edit)
        val btnHapus: ImageView = itemView.findViewById(R.id.btn_hapus)
    }

    // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayerViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return PlayerViewHolder(view)
    }

    // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val data = playerList[position]

        holder.foodName.text = data.name
        holder.foodCategory.text = data.kategori

        // Mengatur aksi ketika item diklik
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(playerList[holder.adapterPosition]) }

        // Mengatur aksi ketika button dihapus
        holder.btnHapus.setOnClickListener {
            onItemClickCallback.onDelete(
                playerList[holder.adapterPosition],
                holder.adapterPosition
            )
        }

        // Mengatur aksi ketika button diupdate
        holder.btnEdit.setOnClickListener { onItemClickCallback.onUpdate(playerList[holder.adapterPosition]) }

    }

    // Fungsi untuk mendapatkan jumlah item
    override fun getItemCount(): Int = playerList.size
}