package com.star.project.mvvmtodo20.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.star.project.mvvmtodo20.R
import com.star.project.mvvmtodo20.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}