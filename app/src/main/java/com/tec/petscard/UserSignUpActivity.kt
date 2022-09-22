package com.tec.petscard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.tec.petscard.databinding.ActivityUserSignUpBinding

class UserSignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserSignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnSignUp.setOnClickListener { signUp() }
    }

    private fun signUp() {
        firebaseAuth.createUserWithEmailAndPassword(binding.etMail.text.toString()
            , binding.etPassword.text.toString()).addOnSuccessListener {
            it.user?.uid?.let { uid -> Log.i("UID:", uid) }
            it.user?.sendEmailVerification()?.addOnSuccessListener {
                Snackbar.make(binding.root, getString(R.string.verification_message_sent),
                    Snackbar.LENGTH_LONG).show()
                Log.i("Correo enviado", "El correo ha sido enviado")
            }?.addOnFailureListener { exception ->
                exception.printStackTrace()
                exception.message?.let { it1 -> Log.i("Error de correo", it1) }
            }
        }.addOnFailureListener {
            it.printStackTrace()
        }
    }
}