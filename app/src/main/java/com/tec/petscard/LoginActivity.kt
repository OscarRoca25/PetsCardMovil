package com.tec.petscard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.tec.petscard.databinding.ActivityLoginBinding
import java.util.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            btnLogin.setOnClickListener { login() }
            etUser.addTextChangedListener { checkField(binding.etUser, binding.tilUser) }
            etPassword.addTextChangedListener { checkField(binding.etPassword, binding.tilPassword) }
            btnSignUp.setOnClickListener { launchRegistrationFragment() }
        }
    }

    private fun launchRegistrationFragment() {
        val intent = Intent(this, UserSignUpActivity::class.java)
        startActivity(intent)
    }

    private fun checkField(editText: TextInputEditText, til: TextInputLayout) {
        if (editText.text.toString().trim().isEmpty())
            til.error = getString(R.string.login_error_field_required)
        else
            til.error = null
    }

    private fun login() {
        if(checkFields()){
            firebaseAuth = FirebaseAuth.getInstance()
            firebaseAuth!!.signInWithEmailAndPassword(
                binding.etUser.text.toString().trim().lowercase(Locale.ROOT)
                , binding.etPassword.text.toString()).addOnCompleteListener {
                if (it.isSuccessful){
                    if(firebaseAuth!!.currentUser?.isEmailVerified == true)
                        Log.i("SuccessfulTask", "Exitoso")
                    else
                        binding.tilUser.error = getString(R.string.login_error_verify_email)
                        Log.i("Not verified account", "Cuenta no verificada")
                }else{
                    it.addOnFailureListener { exception->
                        when(exception.message){
                            Constants.USER_NOT_FOUND_MESSAGE ->{
                                binding.tilUser.error = getString(R.string.user_not_found)
                            }
                            Constants.PASSWORD_NOT_VALID_MESSAGE ->{
                                binding.tilPassword.error = getString(R.string.invalid_password)
                            }
                        }
                        exception.printStackTrace()
                    }
                }
            }
        }
    }

    private fun checkFields(): Boolean {
        var i = 0
        if(binding.etUser.text.toString().trim().isEmpty())
            binding.tilUser.error = getString(R.string.login_error_field_required)
        else{
            val pattern = Pattern.compile(Constants.MAIL_REGEX)
            val matcher = pattern.matcher(binding.etUser.text.toString())
            if(matcher.matches()){
                binding.tilUser.error = null
                i++
            }else{
                binding.tilUser.error = getString(R.string.login_error_user_wrong_format)
            }

        }
        if (binding.etPassword.text.toString().isEmpty())
            binding.tilPassword.error = getString(R.string.login_error_field_required)
        else{
            binding.tilPassword.error = null
            i++
        }
        return i == 2
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}