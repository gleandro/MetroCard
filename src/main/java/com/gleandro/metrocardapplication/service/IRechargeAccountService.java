package com.gleandro.metrocardapplication.service;

import com.gleandro.metrocardapplication.entity.RechargeAccountEntity;
import com.gleandro.metrocardapplication.util.ApiResponse;

import java.util.List;

public interface IRechargeAccountService {

    List<RechargeAccountEntity> getRechargeAccounts(String accountCode, String userCode);

    ApiResponse<RechargeAccountEntity> add(RechargeAccountEntity obj);
    
}
