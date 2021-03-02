package com.cadiducho.cservidoresmc.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class ServerStats {
    @SerializedName("nombre") private String serverName;
    @SerializedName("puesto") private int position;
    @SerializedName("votoshoy") private int dayVotes;
    @SerializedName("votoshoypremiados") private int rewardedDayVotes;
    @SerializedName("votossemanales") private int weekVotes;
    @SerializedName("votossemanalespremiados") private int rewardedWeekVotes;
    @SerializedName("ultimos20votos") private List<ServerVote> lastVotes;
}
