package com.cadiducho.cservidoresmc.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class ServerVote {
    @Getter @SerializedName("usuario") private String name;
    @SerializedName("recompensado") private int recompensado;

    public boolean isRewarded() {
        return recompensado != 0;
    }
}
