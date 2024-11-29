package com.bangkit.agrotentionapp.view.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.bangkit.agrotentionapp.R
import com.bangkit.agrotentionapp.databinding.ActivityMainBinding
import com.bangkit.agrotentionapp.databinding.ActivityWelcomeBinding
import com.bangkit.agrotentionapp.view.intro.IntroActivity
import com.bangkit.agrotentionapp.view.main.MainActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
        }


    }
}