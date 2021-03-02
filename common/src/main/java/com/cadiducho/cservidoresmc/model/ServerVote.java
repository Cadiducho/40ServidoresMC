package com.cadiducho.cservidoresmc.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ServerVote {
    @SerializedName("nombre") private String name;
    @SerializedName("premiado") private boolean rewarded;
}
