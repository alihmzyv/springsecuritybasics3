package com.alihmzyv.springsecuritybasics3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class BalanceController {
    @GetMapping("/balance")
    public String getBalanceDetails() {
        return "account details";
    }
}
