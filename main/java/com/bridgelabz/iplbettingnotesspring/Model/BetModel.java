package com.bridgelabz.iplbettingnotesspring.Model;

import com.bridgelabz.iplbettingnotesspring.dto.BetDTO;
import lombok.Data;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "betting_notes")
public class BetModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long registerId;
    private String userName;
    private Integer userAge;
    private String emailId;
    private String password;
    private Integer matchNumber;
    private LocalDateTime matchDate;
    private String teamA;
    private String teamB;
    private String venue;
    private String userBetTeam;
    private String userBetAmount;

    public BetModel(BetDTO betDTO) {
        this.userName=betDTO.getUserName();
        this.userAge=betDTO.getUserAge();
        this.emailId=betDTO.getEmailId();
        this.password=betDTO.getPassword();
        this.matchNumber=betDTO.getMatchNumber();
        this.teamA=betDTO.getTeamA();
        this.teamB=betDTO.getTeamB();
        this.venue=betDTO.getVenue();
        this.userBetTeam=betDTO.getUserBetTeam();
        this.userBetAmount=betDTO.getUserBetAmount();
    }

    public BetModel() {
    }
}
