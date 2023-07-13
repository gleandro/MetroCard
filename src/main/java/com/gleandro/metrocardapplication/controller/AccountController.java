package com.gleandro.metrocardapplication.controller;

import com.gleandro.metrocardapplication.entity.AccountEntity;
import com.gleandro.metrocardapplication.entity.TransferEntity;
import com.gleandro.metrocardapplication.service.IAccountService;
import com.gleandro.metrocardapplication.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "*")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping("/transactions")
    public List<TransferEntity> getAccountByCodeOrNumber(@RequestParam String filter) {
        return accountService.getAllTrasnsaction(filter);
    }

    @GetMapping
    public List<AccountEntity> getAccounts(@RequestParam String userCode) {
        return accountService.getAccounts(userCode);
    }

    @PostMapping("{userCode}")
    public ApiResponse<AccountEntity> add(@PathVariable String userCode) {
        return accountService.add(userCode);
    }

}
