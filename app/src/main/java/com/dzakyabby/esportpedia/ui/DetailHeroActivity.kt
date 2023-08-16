package com.dzakyabby.esportpedia.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dzakyabby.esportpedia.databinding.ActivityDetailArtikelBinding
import com.dzakyabby.esportpedia.remote.HeroResponseItem

class DetailHeroActivity : AppCompatActivity() {

    companion object {
        val EXTRA_HERO = "extra_hero"
    }

    private lateinit var binding: ActivityDetailArtikelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hero = intent.getSerializableExtra(EXTRA_HERO) as? HeroResponseItem
        if (hero != null) {
            val concatenate = "https://api.opendota.com" + hero.img
            Glide.with(binding.imgPoster.context)
                .load(concatenate)
                .into(binding.imgPoster)
            binding.tvHeroName.text = hero.localizedName
            binding.tvHeroType.text = hero.attackType
            binding.tvJudulHeroPrimaryAttr.text = hero.primaryAttr
            binding.tvHeroBaseHealth.text = hero.baseHealth.toString()
            binding.tvHeroBaseMana.text = hero.baseMana.toString()
            binding.tvHeroRole.text = hero.roles.toString()
        }


    }


}