package com.task.egymtask.model.data_model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopStoriesModel(
    @SerializedName("copyright")
    val copyright: String?,
    @SerializedName("last_updated")
    val lastUpdated: String?,
    @SerializedName("num_results")
    val numResults: Int?,
    @SerializedName("results")
    val results: List<Result>?,
    @SerializedName("section")
    val section: String?,
    @SerializedName("status")
    val status: String?
) : Parcelable {
    @Parcelize
    data class Result(
        @SerializedName("abstract")
        val `abstract`: String?,
        @SerializedName("byline")
        val byline: String?,
        @SerializedName("created_date")
        val createdDate: String?,
        @SerializedName("des_facet")
        val desFacet: List<String?>?,
        @SerializedName("geo_facet")
        val geoFacet: List<String?>?,
        @SerializedName("item_type")
        val itemType: String?,
        @SerializedName("kicker")
        val kicker: String?,
        @SerializedName("material_type_facet")
        val materialTypeFacet: String?,
        @SerializedName("multimedia")
        val multimedia: List<Multimedia?>?,
        @SerializedName("org_facet")
        val orgFacet: List<String?>?,
        @SerializedName("per_facet")
        val perFacet: List<String?>?,
        @SerializedName("published_date")
        val publishedDate: String?,
        @SerializedName("section")
        val section: String?,
        @SerializedName("short_url")
        val shortUrl: String?,
        @SerializedName("subsection")
        val subsection: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("updated_date")
        val updatedDate: String?,
        @SerializedName("uri")
        val uri: String?,
        @SerializedName("url")
        val url: String?
    ) : Parcelable {
        @Parcelize
        data class Multimedia(
            @SerializedName("caption")
            val caption: String?,
            @SerializedName("copyright")
            val copyright: String?,
            @SerializedName("format")
            val format: String?,
            @SerializedName("height")
            val height: Int?,
            @SerializedName("subtype")
            val subtype: String?,
            @SerializedName("type")
            val type: String?,
            @SerializedName("url")
            val url: String?,
            @SerializedName("width")
            val width: Int?
        ) : Parcelable
    }
}