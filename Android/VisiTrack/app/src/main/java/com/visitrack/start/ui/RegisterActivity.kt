package com.visitrack.start.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.visitrack.R
import com.visitrack.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.contentRegister.btnRegister.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.btn_login -> {
                val passwords = binding.contentRegister.edtPassword.text.toString().trim()
                val username = binding.contentRegister.edtUsername.text.toString().trim()
                if (username.equals("")) {
                    binding.contentRegister.edtUsername.error = "Please Enter Your Username"
                } else if (passwords.equals("")) {
                    binding.contentRegister.edtPassword.error = "Please Enter Your Password"
                }
            }
        }
    }
}