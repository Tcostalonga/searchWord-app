package tarsila.costalonga.searchwordapp.ui

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tarsila.costalonga.searchwordapp.utils.Resource
import tarsila.costalonga.searchwordapp.network.WordAPI
import tarsila.costalonga.searchwordapp.network.WordClass
import tarsila.costalonga.searchwordapp.utils.Status
import java.lang.Exception

class MainViewModel @ViewModelInject constructor(val api: WordAPI) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.Main)

    private val _word = MutableLiveData<WordClass>()
    val word: LiveData<WordClass>
        get() = _word

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String>
        get() = _msg


    suspend fun suspendRequest(): Resource<WordClass> {

        return try {
            val retornoAPI = api.getWord("tarsia")
            retornoAPI.code()
            if (retornoAPI.isSuccessful) {
                Resource.success(retornoAPI.body())
            } else {
                Resource.error("Palavra não encontrada", null)
            }

        } catch (e: Exception) {
            Resource.error("Verifique sua conexão", null)
        }
    }

    fun requestNetwork() {
        scope.launch {
            val retorno = suspendRequest()
            _msg.value = retorno.message
        }
    }

    fun checkConection(context: Context) {
        //Checar conexão. Se retornar null, quer dizer que nenhuma rede esta conectada
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val k = cm.activeNetwork

        Log.i("Notconnected", k.toString())
    }
}