//package com.example.ecommerce
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Patterns
//import android.widget.EditText
//import android.widget.Toast
//import com.example.ecommerce.databinding.ActivityLoginBinding
//import com.google.firebase.auth.FirebaseAuth
//
//class LoginActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityLoginBinding
//    private lateinit var firebaseAuth: FirebaseAuth
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        firebaseAuth = FirebaseAuth.getInstance()
//
//        binding.LoginBtn.setOnClickListener {
//            val email = binding.loginEmail.text.toString()
//            val password = binding.loginPassword.text.toString()
//            if (email.isNotEmpty() && password.isNotEmpty()){
//                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
//                    if (it.isSuccessful){
//                        val intent = Intent(this, MainActivity::class.java)
//                        startActivity(intent)
//                    } else {
//                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
//                    }
//                }
//            } else {
//                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        fun compareEmail(email: EditText){
//            if (email.text.toString().isEmpty()){
//                return
//            }
//            if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
//                return
//            }
//            firebaseAuth.sendPasswordResetEmail(email.text.toString())
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        Toast.makeText(this, "Check your email", Toast.LENGTH_SHORT).show()
//                    }
//                }
//        }
//    }
//}

package com.example.ecommerce
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var tvRedirectSignUp: TextView
    lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    lateinit var btnLogin: Button

    // Creating firebaseAuth object
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // View Binding
        tvRedirectSignUp = findViewById(R.id.RedirectSignUp)
        btnLogin = findViewById(R.id.LoginBtn)
        etEmail = findViewById(R.id.loginEmail)
        etPass = findViewById(R.id.loginPassword)

        // initialising Firebase auth object
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            login()
        }

        tvRedirectSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            // using finish() to end the activity
            finish()
        }
    }

    private fun login() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Log In failed", Toast.LENGTH_SHORT).show()
            } else
                btnLogin.setOnClickListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                Toast.makeText(this, "Successfully LoggedIn ", Toast.LENGTH_SHORT).show()
        }
    }

}
