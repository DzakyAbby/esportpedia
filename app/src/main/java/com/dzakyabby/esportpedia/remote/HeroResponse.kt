package com.dzakyabby.esportpedia.remote

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HeroResponse(
	@field:SerializedName("heroResponse")
	val heroResponse: List<HeroResponseItem>
): Serializable

data class HeroResponseItem(

	@field:SerializedName("primary_attr")
	val primaryAttr: String,

	@field:SerializedName("attack_range")
	val attackRange: Int,

	@field:SerializedName("attack_type")
	val attackType: String,

	@field:SerializedName("base_health")
	val baseHealth: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("img")
	val img: String,

	@field:SerializedName("roles")
	val roles: List<String>,

	@field:SerializedName("base_mana")
	val baseMana: Int,

	@field:SerializedName("localized_name")
	val localizedName: String,

	@field:SerializedName("hero_id")
	val heroId: Int
): Serializable
