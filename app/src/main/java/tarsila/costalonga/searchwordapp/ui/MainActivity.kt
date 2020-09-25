package tarsila.costalonga.searchwordapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import tarsila.costalonga.searchwordapp.R
import tarsila.costalonga.searchwordapp.databinding.ActivityMainBinding
import tarsila.costalonga.searchwordapp.utils.EMPTY_BODY_REQUEST
import tarsila.costalonga.searchwordapp.utils.NOT_CONNECTED_REQUEST
import tarsila.costalonga.searchwordapp.utils.NOT_FOUND_REQUEST

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    lateinit var adapter: WordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = viewModel

        binding.searchButton.setOnClickListener {
            val k = binding.textInput.text.toString()
            viewModel.requestNetwork(k)
            binding.cardView.visibility = View.INVISIBLE
        }

        //Setando dados na tela após retorno da requisição
        viewModel.word.observe(this, Observer {

            setarRecyclerView()
            adapter.data = it.definitions
            adapter.notifyDataSetChanged()

            binding.word.text = it.word
            binding.pronunciation.text = getString(R.string.stringPronunciation, it.pronunciation)
            binding.cardView.visibility = View.VISIBLE
        })

        viewModel.msg.observe(this, Observer {
            binding.error.text = it

            when (it) {
                EMPTY_BODY_REQUEST -> binding.imgError.setImageResource(R.drawable.b_shadow)
                NOT_FOUND_REQUEST -> binding.imgError.setImageResource(R.drawable.b_shadow)
                NOT_CONNECTED_REQUEST -> binding.imgError.setImageResource(R.drawable.wifi_off)
            }


        })
    }

    fun setarRecyclerView() {
        adapter = WordAdapter()
        binding.recView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recView.adapter = adapter

    }
}
