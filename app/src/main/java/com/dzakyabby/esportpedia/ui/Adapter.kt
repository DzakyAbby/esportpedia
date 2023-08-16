package com.dzakyabby.esportpedia.ui

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dzakyabby.esportpedia.R
import com.dzakyabby.esportpedia.remote.HeroResponseItem



class Adapter(private val Heroeslist: List<HeroResponseItem>):
    RecyclerView.Adapter<Adapter.HeroesViewHolder>()  {

    inner class HeroesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val heroName: TextView = itemView.findViewById(R.id.tv_item_title)
        private val attackType: TextView = itemView.findViewById(R.id.tv_item_atk_type)
        private val image: ImageView = itemView.findViewById(R.id.img_poster)
        private val cvItemHero: View = itemView.findViewById(R.id.cv_item_artikel)

        fun bind(hero: HeroResponseItem) {
            heroName.text = hero.localizedName
            attackType.text = hero.attackType
            Log.d("Adapterrr", "bind: ${hero.attackType}")

            val concatenate = "https://api.opendota.com" + hero.img

            Glide.with(itemView.context)
                .load(concatenate)
                .into(image)

            Log.d("Adapterrr", "bind: ${concatenate}")
            cvItemHero.setOnClickListener {
                val intent = Intent(it.context, DetailHeroActivity::class.java)
                intent.putExtra(DetailHeroActivity.EXTRA_HERO, hero)
                it.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hero, parent, false)
        return HeroesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: Adapter.HeroesViewHolder, position: Int) {
        val currentHero = Heroeslist[position]
        holder.bind(currentHero)
    }

    override fun getItemCount(): Int {
        Log.d("Adapterrr", "getItemCount: ${Heroeslist.size}")
        return Heroeslist.size
    }

}