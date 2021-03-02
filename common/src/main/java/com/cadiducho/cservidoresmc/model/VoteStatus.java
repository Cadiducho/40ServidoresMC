package com.cadiducho.cservidoresmc.model;

import com.google.gson.annotations.SerializedName;

public enum VoteStatus {
    @SerializedName("0") NOT_VOTED,
    @SerializedName("1") SUCCESS,
    @SerializedName("2") ALREADY_VOTED,
    @SerializedName("3") INVALID_kEY,
}
