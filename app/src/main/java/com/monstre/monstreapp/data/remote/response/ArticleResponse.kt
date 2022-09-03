package com.monstre.monstreapp.data.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName

data class ArticleResponse(

	@field:SerializedName("data")
	val data: ArrayList<ArticleItem>,

	@field:SerializedName("status")
	val status: String
)
@Parcelize
data class ArticleItem(

	@field:SerializedName("title")
	val title: String?="",

	@field:SerializedName("desc")
	val desc: String?=""
): Parcelable
