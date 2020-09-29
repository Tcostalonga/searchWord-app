package tarsila.costalonga.searchwordapp.ui.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_info.*
import tarsila.costalonga.searchwordapp.R

class InfoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val fragAdapter = PagerAdapter(supportFragmentManager)
        view_pager.adapter = fragAdapter

        //Setando titulos para as abas
          tab_layout.setupWithViewPager(view_pager)
    }
}