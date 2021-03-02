package com.cadiducho.cservidoresmc.model;

import lombok.Data;

@Data
public class VoteResponse {
    private final String web;
    private final VoteStatus status;
}
