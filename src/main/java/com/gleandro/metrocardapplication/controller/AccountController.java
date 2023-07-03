package com.gleandro.metrocardapplication.controller;

import com.gleandro.metrocardapplication.entity.AccountEntity;
import com.gleandro.metrocardapplication.service.AccountService;
import com.gleandro.metrocardapplication.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "*")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<AccountEntity> getAccounts(@RequestParam String userCode) {
        return accountService.getAccounts(userCode);
    }

    @PostMapping("{userCode}")
    public ApiResponse<AccountEntity> add(@PathVariable String userCode) {
        return accountService.add(userCode);
    }


}
