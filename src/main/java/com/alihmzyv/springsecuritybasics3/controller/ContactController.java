package com.alihmzyv.springsecuritybasics3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ContactController {
    @GetMapping("/contact")
    public String getContactDetails() {
        return "account details";
    }
}
