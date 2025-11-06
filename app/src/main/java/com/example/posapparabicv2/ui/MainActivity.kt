
package com.example.posapparabicv2.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.posapparabicv2.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnOpenTill.setOnClickListener { startActivity(Intent(this, SaleActivity::class.java)) }
        binding.btnProducts.setOnClickListener { startActivity(Intent(this, ProductListActivity::class.java)) }
    }
}
