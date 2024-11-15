package com.example.mapping.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {

    @Autowired
    private final AccountService accountService;

    @GetMapping("/transfer")
    public String transfer(){
        return "transfer_form";
    }

    @PostMapping("/transfer")
    public String transfer(@ModelAttribute TransferForm transferForm, Model model){
        model.addAttribute("message", accountService.transfer(transferForm));
        return "transfer_form";
    }
}
