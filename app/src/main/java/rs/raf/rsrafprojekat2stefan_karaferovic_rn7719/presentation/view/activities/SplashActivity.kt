package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences: SharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE)
        val username = sharedPreferences.getString(LoginActivity.USERNAME_CODE, null);
        val password = sharedPreferences.getString(LoginActivity.PASSWORD_CODE, null)
        lateinit var intent: Intent

        intent = if (username == null || password == null) {
            Intent(this, LoginActivity::class.java)
        } else {
            Intent(this, MainActivity::class.java)
        }

        startActivity(intent)
        finish()
    }
}