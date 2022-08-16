package com.bridgelabz.iplbettingnotesspring.controller;

import com.bridgelabz.iplbettingnotesspring.Model.BetModel;
import com.bridgelabz.iplbettingnotesspring.dto.BetDTO;
import com.bridgelabz.iplbettingnotesspring.service.IBetService;
import com.bridgelabz.iplbettingnotesspring.util.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class BetController {

    @Autowired
    IBetService iBetService;

    //Welcome message;
    @GetMapping ("/welcome")
    public String welcomeMessage(){
        return "Welcome to Ipl Betting Notes Spring ";
    }

    //creating user
    @PostMapping ("userBetRegistration")
    public BetModel createUserBet(@RequestBody BetDTO betDTO){
        return iBetService.betRegistration(betDTO);
    }

    //updating user
    @PutMapping("/updateBet/{registerId}")
    public BetModel updateBet(@PathVariable Long registerId,@RequestParam String token ,@RequestBody BetDTO betDTO ){
        return iBetService.editBet(registerId,token,betDTO);
    }

    //retrieve data
    @GetMapping("list")
    public List<BetModel> getList(@RequestParam String token){
        return iBetService.viewList(token);
    }

    //delete user
    @DeleteMapping("delete/{id}")
    public BetModel deleteUser(@PathVariable Long id ,@RequestParam String token){
        return iBetService.delete(id,token);
    }

    //Login to get token by using emailId and Password
    @PostMapping("/login")
    public ResponseClass login(@RequestParam String email,@RequestParam String password){
        return iBetService.loginToken(email,password);
    }
}
