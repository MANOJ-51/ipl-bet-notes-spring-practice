package com.bridgelabz.iplbettingnotesspring.dto;

import lombok.Data;

@Data
public class BetDTO {
    private Long registerId;
    private String userName;
    private Integer userAge;
    private String emailId;
    private String password;
    private Integer matchNumber;
    private String teamA;
    private String teamB;
    private String venue;
    private String userBetTeam;
    private String userBetAmount;
}
