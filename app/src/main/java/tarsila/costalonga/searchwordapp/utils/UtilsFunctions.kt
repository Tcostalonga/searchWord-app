package tarsila.costalonga.searchwordapp.utils

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import tarsila.costalonga.searchwordapp.R

const val EMPTY_BODY_REQUEST = "Não há informação para a palavra pesquisada"
const val NOT_FOUND_REQUEST = "Palavra não encontrada"
const val NOT_CONNECTED_REQUEST = "Verifique sua conexão"


fun makeToast(context: Context, msg: String): Unit {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun View.hideKeyboard() {
    val inputManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard() {
    val inputManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)

}

fun formatExample(string: String?): String {
    return if (!string.isNullOrEmpty()) {
        ("\"$string\"")
    } else ("-")


}


