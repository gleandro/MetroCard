package com.gleandro.metrocardapplication.service;

import com.gleandro.metrocardapplication.entity.AccountEntity;
import com.gleandro.metrocardapplication.entity.RechargeAccountEntity;
import com.gleandro.metrocardapplication.repository.RechargeAccountRepository;
import com.gleandro.metrocardapplication.util.ApiResponse;
import com.gleandro.metrocardapplication.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class RechargeAccountService {

    @Autowired
    private RechargeAccountRepository rechargeAccountRepository;

    @Autowired
    private AccountService accountService;

    public List<RechargeAccountEntity> getRechargeAccounts(String accountCode, String userCode) {
        List<RechargeAccountEntity> listResponse = rechargeAccountRepository.findByUserCodeOrderByCreatedDateDesc(accountCode, userCode);
        listResponse = listResponse.stream().map(rechargeAccountEntity -> {
            rechargeAccountEntity.setAccountNumber(rechargeAccountEntity.getAccountEntity().getAccountNumber());
            return rechargeAccountEntity;
        }).toList();
        return listResponse;
    }

    public ApiResponse<RechargeAccountEntity> add(RechargeAccountEntity obj) {

        obj.setAccountCode(obj.getAccountCode());
        obj.setAmount(obj.getAmount());
        obj.setCreatedDate(LocalDateTime.now(ZoneId.of(Constants.TIME_ZONE_DEFAULT)));

        AccountEntity accountEntity = accountService.getAccountByCode(obj.getAccountCode()).getData();
        accountEntity.setBalance(accountEntity.getBalance() + obj.getAmount());

        RechargeAccountEntity rechargeAccountEntity = rechargeAccountRepository.save(obj);
        rechargeAccountEntity.setAccountNumber(accountEntity.getAccountNumber());
        return buildResponse(true, Constants.SUCCESS, Constants.RECHARGE_ACCOUNT_CREATED, rechargeAccountEntity);
    }

    private ApiResponse<RechargeAccountEntity> buildResponse(boolean status, String code, String message, RechargeAccountEntity entity) {
        ApiResponse<RechargeAccountEntity> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(status);
        apiResponse.setCode(code);
        apiResponse.setMessage(message);
        apiResponse.setData(entity);
        return apiResponse;
    }

}
