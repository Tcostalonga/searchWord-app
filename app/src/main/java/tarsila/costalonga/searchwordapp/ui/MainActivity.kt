package tarsila.costalonga.searchwordapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import tarsila.costalonga.searchwordapp.R
import tarsila.costalonga.searchwordapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = viewModel

   //     binding.cardView.visibility = View.INVISIBLE

        //     word.text = viewModel.k.value
//search_txt.hint = viewModel.k.value

/*        Picasso.get()
            .load(R.drawable.cloud_img)
            .transform(CropCircleTransformation())
            .into()*/

    }
}