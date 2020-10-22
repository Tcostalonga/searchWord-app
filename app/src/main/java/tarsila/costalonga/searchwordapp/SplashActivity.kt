package tarsila.costalonga.searchwordapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tarsila.costalonga.searchwordapp.ui.main.MainActivity

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}