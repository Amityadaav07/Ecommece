package com.example.ecommerce
import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    
    private lateinit var bottomNavigationView: BottomNavigationView
    private val firstFragment = FirstFragment()
    private val secondFragment = SecondFragment()
    private val thirdFragment = ThirdFragment()
    private val fourthFragment = FourthFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener(this)
        bottomNavigationView.selectedItemId = R.id.home
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                supportFragmentManager.beginTransaction().replace(R.id.flFragment, firstFragment).commit()
                return true
            }
            R.id.Chat -> {
                supportFragmentManager.beginTransaction().replace(R.id.flFragment, secondFragment).commit()
                return true
            }
            R.id.Cart -> {
                supportFragmentManager.beginTransaction().replace(R.id.flFragment, thirdFragment).commit()
                return true
            }
            R.id.Profile -> {
                supportFragmentManager.beginTransaction().replace(R.id.flFragment, fourthFragment).commit()
                return true
            }
        }
        return false
    }
}
