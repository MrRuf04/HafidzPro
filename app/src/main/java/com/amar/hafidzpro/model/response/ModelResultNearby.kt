package com.amar.hafidzpro.model.response

import com.google.gson.annotations.SerializedName
import com.amar.hafidzpro.model.nearby.ModelResults


class ModelResultNearby {
    @SerializedName("results")
    lateinit var modelResults: List<ModelResults>
}