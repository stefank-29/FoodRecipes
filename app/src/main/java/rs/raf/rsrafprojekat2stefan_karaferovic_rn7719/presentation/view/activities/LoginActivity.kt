package rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.presentation.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.ActivityLoginBinding
import rs.raf.rsrafprojekat2stefan_karaferovic_rn7719.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    companion object {
        const val USERNAME_CODE = "username"
        const val PASSWORD_CODE = "password"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initListeners()
    }

    private fun initListeners() {
        binding.loginButton.setOnClickListener { v: View? ->
            val name = binding.nameEt.text.toString()
            val password = binding.passwordEt.text.toString()
            var valid = true
            if (name == "") {
                Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show()
                valid = false
            }
            if (password == "") {
                Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show()
                valid = false
            }
            if (!valid) {
                return@setOnClickListener
            }


            val sharedPreferences =
                getSharedPreferences(packageName, MODE_PRIVATE)
            sharedPreferences
                .edit()
                .putString(USERNAME_CODE, name)
                .apply()
            sharedPreferences
                .edit()
                .putString(PASSWORD_CODE, password)
                .apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}