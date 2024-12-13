package com.inforcap.exampleapiresttmdb.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.inforcap.exampleapiresttmdb.databinding.ActivityMainBinding
import com.inforcap.exampleapiresttmdb.viewmodel.MansionViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MansionViewModel by viewModels()
    private lateinit var adapterMansion: AdapterMansion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        // Observa los cambios en la lista de mansiones primero
        viewModel.mansionList.observe(this) { mansions ->
            Log.d("MainActivity", "Mansions: $mansions") // Log para verificar
            if (mansions != null && mansions.isNotEmpty()) {
                adapterMansion.updateMansionList(mansions) // Actualiza el adaptador con los datos
            } else {
                Log.e("MainActivity", "No se encontraron mansiones")
            }
        }

        // Luego llama al método para obtener mansiones
        fetchMansions()
    }

    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(this, 3)
        binding.rvMansions.layoutManager = layoutManager
        adapterMansion = AdapterMansion(this, listOf())  // Inicializa el adaptador con una lista vacía
        binding.rvMansions.adapter = adapterMansion
    }

    private fun fetchMansions() {
        // Llama al método en el ViewModel para obtener mansiones
        Log.d("MainActivity", "Llamando a fetchMansions()")
        viewModel.getMansions()
    }
}