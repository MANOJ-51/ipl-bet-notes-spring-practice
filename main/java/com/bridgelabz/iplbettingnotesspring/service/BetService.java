package com.bridgelabz.iplbettingnotesspring.service;

import com.bridgelabz.iplbettingnotesspring.Model.BetModel;
import com.bridgelabz.iplbettingnotesspring.dto.BetDTO;
import com.bridgelabz.iplbettingnotesspring.exception.UserNotFound;
import com.bridgelabz.iplbettingnotesspring.repository.IBetRepository;
import com.bridgelabz.iplbettingnotesspring.util.ResponseClass;
import com.bridgelabz.iplbettingnotesspring.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BetService implements IBetService {

    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    IBetRepository iBetRepository;

    @Autowired
    MailService mailService;

    @Override
    public BetModel betRegistration(BetDTO betDTO) {
        BetModel betModel = new BetModel(betDTO);
        betModel.setMatchDate(LocalDateTime.now());
        iBetRepository.save(betModel);
        String body = "User Bet Registration Is Successful with id :-"+betDTO.getRegisterId()+"\n"+betDTO;
        String subject = " User Bet Registration Success";
        mailService.send(betDTO.getEmailId(), body,subject);
        return betModel;
    }

    @Override
    public BetModel editBet(Long registerId, String token, BetDTO betDTO) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<BetModel> isBetPresent = iBetRepository.findById(userId);
        if (isBetPresent.isPresent()){
            isBetPresent.get().setUserName(betDTO.getUserName());
            isBetPresent.get().setUserAge(betDTO.getUserAge());
            isBetPresent.get().setEmailId(betDTO.getEmailId());
            isBetPresent.get().setPassword(betDTO.getPassword());
            isBetPresent.get().setMatchNumber(betDTO.getMatchNumber());
            isBetPresent.get().setTeamA(betDTO.getTeamA());
            isBetPresent.get().setTeamB(betDTO.getTeamB());
            isBetPresent.get().setVenue(betDTO.getVenue());
            isBetPresent.get().setUserBetTeam(betDTO.getUserBetTeam());
            isBetPresent.get().setUserBetAmount(betDTO.getUserBetAmount());
            iBetRepository.save(isBetPresent.get());
            String body = "User Bet Registration Is updated Successful with id :-"+isBetPresent.get().getRegisterId()+"\n"+isBetPresent.get();
            String subject = " User Bet Registration updated Success";
            mailService.send(isBetPresent.get().getEmailId(), body,subject);
            return isBetPresent.get();
        }
        throw new UserNotFound(400,"User Not Found");
    }

    @Override
    public List<BetModel> viewList(String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<BetModel> isToken = iBetRepository.findById(userId);
        if (isToken.isPresent()) {
            List<BetModel> isBetList = iBetRepository.findAll();
            if (isBetList.size() > 0) {
                return isBetList;
            }
        }
        throw new UserNotFound(400,"User Not Found");
    }

    @Override
    public BetModel delete(Long id, String token) {
        Long userId = tokenUtil.decodeToken(token);
        Optional<BetModel> isUserPresent = iBetRepository.findById(userId);
        if (isUserPresent.isPresent()){
            iBetRepository.delete(isUserPresent.get());
            String body = "User Bet Registration Is Successful with id :-"+isUserPresent.get().getRegisterId()+"\n"+isUserPresent.get();
            String subject = " User Bet Registration Success";
            mailService.send(isUserPresent.get().getEmailId(), body,subject);
            return isUserPresent.get();
        }
        throw new UserNotFound(400,"User Not Found");
    }

    @Override
    public ResponseClass loginToken(String email, String password) {
        Optional<BetModel> isBetEmailPresent =iBetRepository.findByEmailId(email);
        if (isBetEmailPresent.isPresent()){
            if (isBetEmailPresent.get().getPassword().equals(password)){
                String token = tokenUtil.createToken(isBetEmailPresent.get().getRegisterId());
                return new ResponseClass(200,"Login Sucess",token);
            }else{
                throw new UserNotFound(400,"Password is wrong");
            }
        }
        throw new UserNotFound(400,"No Bet Data Found");
    }
}
