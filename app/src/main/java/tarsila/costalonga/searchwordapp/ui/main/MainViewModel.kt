package tarsila.costalonga.searchwordapp.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tarsila.costalonga.searchwordapp.network.WordAPI
import tarsila.costalonga.searchwordapp.network.WordClass
import tarsila.costalonga.searchwordapp.utils.*

class MainViewModel @ViewModelInject constructor(val api: WordAPI) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.Main)

    private val _word = MutableLiveData<WordClass>()
    val word: LiveData<WordClass> = _word

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String>
        get() = _msg


    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    suspend fun suspendRequest(param: String): Resource<WordClass> {

        return try {
            val retornoAPI = api.getWord(param)
            if (retornoAPI.isSuccessful) {
                retornoAPI.body()?.let {
                    _word.value = retornoAPI.body()
                    return@let Resource.success(retornoAPI.body())
                } ?: Resource.error(EMPTY_BODY_REQUEST, null)
            } else {
                Resource.error(NOT_FOUND_REQUEST, null)
            }
        } catch (e: Exception) {
            Resource.error(NOT_CONNECTED_REQUEST, null)
        }
    }

    fun requestNetwork(param: String) {
        scope.launch {
            val retorno = suspendRequest(param)
            _msg.value = retorno.message
        }
    }

/*    fun checkConection(context: Context) {
        //Checar conexão. Se retornar null, quer dizer que nenhuma rede esta conectada
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val k = cm.activeNetwork

        Log.i("Notconnected", k.toString())
    }*/
}