package com.inforcap.exampleapiresttmdb.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inforcap.exampleapiresttmdb.model.MansionSummaryEntity
import com.inforcap.exampleapiresttmdb.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MansionViewModel(application: Application) : AndroidViewModel(application) {
    // Lista de objetos MansionSummaryEntity
    private val _mansionList = MutableLiveData<List<MansionSummaryEntity>>(emptyList())
    val mansionList: LiveData<List<MansionSummaryEntity>> get() = _mansionList

    private val context: Context = application.applicationContext

    // Método para obtener la lista de mansiones
    fun getMansions() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Llama al método para obtener mansiones
                Log.d("MansionViewModel", "Iniciando la llamada a la API para obtener mansiones.")
                val response = RetrofitClient.apiService.getMansions()

                withContext(Dispatchers.Main) {
                    Log.d("MansionViewModel", "Código de respuesta: ${response.code()}") // Log del código de respuesta
                    if (response.isSuccessful) {
                        val mansions = response.body() // Ahora esto es directamente la lista de mansiones
                        Log.d("MansionViewModel", "Mansions: $mansions") // Imprime la respuesta
                        _mansionList.value = mansions ?: emptyList() // Maneja el caso nulo
                    } else {
                        Log.e("MansionViewModel", "Error en la respuesta: ${response.errorBody()?.string()}")
                        Toast.makeText(context, "Error en la respuesta de la API: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: IOException) {
                Log.e("MansionViewModel", "Error de red: ${e.message}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Error de red: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("MansionViewModel", "Error inesperado: ${e.message}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Error inesperado. Intenta nuevamente.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}