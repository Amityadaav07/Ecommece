//package com.example.ecommerce
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Toast
//import com.example.ecommerce.databinding.ActivitySignUpBinding
//import com.google.firebase.auth.FirebaseAuth
//
//class SignUpActivity : AppCompatActivity() {
//    private lateinit var binding: ActivitySignUpBinding
//    private lateinit var firebaseAuth: FirebaseAuth
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivitySignUpBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        firebaseAuth = FirebaseAuth.getInstance()
//
//        binding.SignupBtn.setOnClickListener{
//            val email = binding.SignUpEmail.text.toString()
//            val password = binding.SignUpPassword.text.toString()
//            val confirmPassword = binding.SignUpConfirmPassword.text.toString()
//            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
//                if (password == confirmPassword){
//                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
//                        if (it.isSuccessful){
//                            val intent = Intent(this, LoginActivity::class.java)
//                            startActivity(intent)
//                        } else {
//                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                } else {
//                    Toast.makeText(this, "Password does not matched", Toast.LENGTH_SHORT).show()
//                }
//            } else {
//                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
//            }
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    lateinit var etEmail: EditText
    lateinit var etConfPass: EditText
    private lateinit var etPass: EditText
    private lateinit var btnSignUp: Button
    lateinit var tvRedirectLogin: TextView

    // create Firebase authentication object
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // View Bindings
        etEmail = findViewById(R.id.SignUpEmail)
        etConfPass = findViewById(R.id.SignUpConfirmPassword)
        etPass = findViewById(R.id.SignUpPassword)
        btnSignUp = findViewById(R.id.SignupBtn)
        tvRedirectLogin = findViewById(R.id.tvRedirectLogin)

        // Initialising auth object
        auth = Firebase.auth

        btnSignUp.setOnClickListener {
            signUpUser()
        }

        // switching from signUp Activity to Login Activity
        tvRedirectLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun signUpUser() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val confirmPassword = etConfPass.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPassword) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                btnSignUp.setOnClickListener {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

