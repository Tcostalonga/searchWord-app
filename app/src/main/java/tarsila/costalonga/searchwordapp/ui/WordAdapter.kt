package tarsila.costalonga.searchwordapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.list_item.view.*
import tarsila.costalonga.searchwordapp.R
import tarsila.costalonga.searchwordapp.network.Definitions
import tarsila.costalonga.searchwordapp.network.WordClass

class WordAdapter : RecyclerView.Adapter<WordAdapter.WordsViewHolder>() {

     var data  = listOf<Definitions>()


    class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Definitions) {
            itemView.type_txv.text = item.type
            itemView.definition_txv.text = item.definition
            itemView.example_txv.text = item.example

            if (item.imageURL.isNullOrEmpty()) {
                itemView.foto_img.visibility = View.GONE
            } else {
                Picasso.get()
                    .load(item.imageURL)
                    .transform(CropCircleTransformation())
                    .into(itemView.foto_img)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordAdapter.WordsViewHolder {

        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return WordAdapter.WordsViewHolder(item)

    }

    override fun onBindViewHolder(holder: WordAdapter.WordsViewHolder, position: Int) {
        val item = data.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
