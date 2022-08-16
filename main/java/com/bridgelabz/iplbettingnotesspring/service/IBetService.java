package com.bridgelabz.iplbettingnotesspring.service;

import com.bridgelabz.iplbettingnotesspring.Model.BetModel;
import com.bridgelabz.iplbettingnotesspring.dto.BetDTO;
import com.bridgelabz.iplbettingnotesspring.util.ResponseClass;

import java.util.List;

public interface IBetService {
    BetModel betRegistration(BetDTO betDTO);

    BetModel editBet(Long registerId, String token, BetDTO betDTO);

    List<BetModel> viewList(String token);

    BetModel delete(Long id, String token);

    ResponseClass loginToken(String email, String password);
}
