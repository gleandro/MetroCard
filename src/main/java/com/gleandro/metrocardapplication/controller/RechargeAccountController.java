package com.gleandro.metrocardapplication.controller;

import com.gleandro.metrocardapplication.entity.RechargeAccountEntity;
import com.gleandro.metrocardapplication.service.IRechargeAccountService;
import com.gleandro.metrocardapplication.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recharge_account")
@CrossOrigin(origins = "*")
public class RechargeAccountController {

    @Autowired
    private IRechargeAccountService rechargeAccountService;

    @GetMapping()
    public List<RechargeAccountEntity> getRechargeAccounts(@RequestParam(required = false) String accountCode, @RequestParam(required = false) String userCode) {
        return rechargeAccountService.getRechargeAccounts(accountCode, userCode);
    }


    @PostMapping
    public ApiResponse<RechargeAccountEntity> add(@RequestBody RechargeAccountEntity rechargeAccountEntity) {
        return rechargeAccountService.add(rechargeAccountEntity);
    }


}
