package tarsila.costalonga.searchwordapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import tarsila.costalonga.searchwordapp.R
import tarsila.costalonga.searchwordapp.databinding.ActivityMainBinding
import tarsila.costalonga.searchwordapp.ui.about.InfoActivity
import tarsila.costalonga.searchwordapp.utils.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    lateinit var adapter: WordAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = viewModel

        binding.searchButton.isEnabled = false

        binding.textInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Esconde o texto e img de erro
                binding.imgError.visibility = View.INVISIBLE
                binding.txtError.visibility = View.INVISIBLE
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Botao só é habilitado com mais de 1 caracter escrito
                binding.searchButton.isEnabled = p1 > 0
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.searchButton.setOnClickListener {

            it.hideKeyboard()
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
            binding.cardView.visibility = View.VISIBLE
        })

        viewModel.msg.observe(this, Observer {

            //Mostra o texto e img de erro
            binding.txtError.visibility = View.VISIBLE
            binding.imgError.visibility = View.VISIBLE

            binding.txtError.text = it

            when (it) {
                EMPTY_BODY_REQUEST -> binding.imgError.setImageResource(R.drawable.b_shadow)
                NOT_FOUND_REQUEST -> binding.imgError.setImageResource(R.drawable.b_shadow)
                NOT_CONNECTED_REQUEST -> binding.imgError.setImageResource(R.drawable.wifi_off)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.opt_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, InfoActivity::class.java)

        if (item.itemId == R.id.about) {
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)

    }

    fun setarRecyclerView() {
        adapter = WordAdapter()
        binding.recView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recView.adapter = adapter

    }

}
