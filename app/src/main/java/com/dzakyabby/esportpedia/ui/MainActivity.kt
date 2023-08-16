package com.dzakyabby.esportpedia.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dzakyabby.esportpedia.databinding.ActivityMainBinding
import com.dzakyabby.esportpedia.remote.HeroResponseItem
import com.dzakyabby.esportpedia.remote.ResultState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var mainViewModel: MainViewModel
    private lateinit var heroAdapter: Adapter // Declare the adapter here
    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        mainViewModel = MainViewModel()

        mainViewModel.getHero()

        recyclerView = binding.rvHero

        recyclerView.setHasFixedSize(true)


        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)



        mainViewModel.HeroResponse.observe(this) {
            when (it) {
                is ResultState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val heroList = it
                    heroAdapter = Adapter(heroList.value as List<HeroResponseItem>)
                    recyclerView.adapter = heroAdapter
                }
                is ResultState.Failure -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
                is ResultState.Loading -> {
                    //Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.VISIBLE
                }

                else -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}