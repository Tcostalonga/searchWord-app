package tarsila.costalonga.searchwordapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.list_item.view.*
import tarsila.costalonga.searchwordapp.R
import tarsila.costalonga.searchwordapp.network.WordClass

class WordAdapter : RecyclerView.Adapter<WordAdapter.WordsViewHolder>() {

    var data = listOf<WordClass>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: WordClass) {
            itemView.word.text = item.word
            itemView.definition_txv.text = item.definitions[adapterPosition].definition
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {

        val item = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return WordsViewHolder(item)

    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}