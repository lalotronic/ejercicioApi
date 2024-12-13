package com.inforcap.exampleapiresttmdb.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inforcap.exampleapiresttmdb.databinding.ItemMansionBinding // Asegúrate de que este sea el binding correcto
import com.inforcap.exampleapiresttmdb.model.MansionSummaryEntity

class AdapterMansion(
    private val context: Context,
    private var mansionList: List<MansionSummaryEntity> // Lista de mansiones
) : RecyclerView.Adapter<AdapterMansion.MansionViewHolder>() {

    private lateinit var binding: ItemMansionBinding // Cambia a el binding correcto para mansiones

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MansionViewHolder {
        binding = ItemMansionBinding.inflate(LayoutInflater.from(context), parent, false)
        return MansionViewHolder(binding)
    }

    override fun getItemCount(): Int = mansionList.size

    override fun onBindViewHolder(holder: MansionViewHolder, position: Int) {
        holder.onBind(mansionList[position])
    }

    inner class MansionViewHolder(private val binding: ItemMansionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(mansion: MansionSummaryEntity) {
            try {
                binding.run {
                    Glide.with(context)
                        .load(mansion.photo)
                        .into(ivImage) // Asegúrate de que ivImage sea el ID correcto en tu layout

                    tvName.text = mansion.name // Asegúrate de que tvName sea el ID correcto
                    tvPrice.text = "Price: \$${mansion.price}" // Asegúrate de que tvPrice sea el ID correcto
                }
            } catch (e: Exception) {
                Log.e("AdapterMansion", "Error al vincular datos: ${e.message}")
                // Maneja el error según sea necesario, como mostrar un valor predeterminado
            }
        }
    }

    // Método para actualizar la lista de mansiones
    fun updateMansionList(newMansionList: List<MansionSummaryEntity>) {
        mansionList = newMansionList
        notifyDataSetChanged() // Notifica que los datos han cambiado
    }

    // Método para actualizar la lista con un solo objeto (opcional)
    fun updateMansion(mansion: MansionSummaryEntity) {
        mansionList = listOf(mansion) // Envolviendo el objeto en una lista
        notifyDataSetChanged() // Notifica que los datos han cambiado
    }
}