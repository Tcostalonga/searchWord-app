package tarsila.costalonga.searchwordapp.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WordClass(
    val definitions: List<Definitions>,
    val word: String,
    val pronunciation: String = ""
) : Parcelable {
}

@Parcelize
data class Definitions(
    val type: String,
    val definition: String,
    val example: String = "",
    @SerializedName("image_url") val imageURL: String = "",
) : Parcelable {

}