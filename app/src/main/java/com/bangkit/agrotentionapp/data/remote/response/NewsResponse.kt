package com.bangkit.agrotentionapp.data.remote.response

import com.google.gson.annotations.SerializedName

 class NewsResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: Any
)

 class DataItem(

	@field:SerializedName("link")
	val link: String,

	@field:SerializedName("accessed_by_server_at")
	val accessedByServerAt: Int,

	@field:SerializedName("publisher")
	val publisher: String,

	@field:SerializedName("time")
	val time: String,

	@field:SerializedName("title")
	val title: String
)
