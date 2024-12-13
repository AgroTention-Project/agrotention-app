package com.bangkit.agrotentionapp.data.remote.response

data class DiseasesResponse(
	val data: Data? = null,
	val success: Boolean? = null,
	val message: String? = null
)

data class Classification(
	val phylum: String? = null,
	val genus: String? = null,
	val species: String? = null,
	val family: String? = null,
	val kingdom: String? = null,
	val jsonMemberClass: String? = null,
	val order: String? = null
)

data class Data(
	val name: Name? = null,
	val description: String? = null,
	val classification: Classification? = null
)

data class Name(
	val scientific: String? = null,
	val national: String? = null,
	val local: String? = null
)

