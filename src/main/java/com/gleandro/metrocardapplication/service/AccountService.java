package com.gleandro.metrocardapplication.service;

import com.gleandro.metrocardapplication.entity.AccountEntity;
import com.gleandro.metrocardapplication.repository.AccountRepository;
import com.gleandro.metrocardapplication.util.ApiResponse;
import com.gleandro.metrocardapplication.util.Constants;
import com.gleandro.metrocardapplication.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<AccountEntity> getAccounts() {
        return accountRepository.findAll();
    }

    public ApiResponse<AccountEntity> getAccountByCode(String accountCode) {
        return buildResponse(true, Constants.SUCCESS, Constants.USER_CREATED, accountRepository.getByAccountCode(accountCode).get());
    }

    public ApiResponse<AccountEntity> add(String userCode) {

        AccountEntity obj = new AccountEntity();
        obj.setAccountCode(Constants.ACCOUNT_PREFIX + Util.formatCodeNumber(accountRepository.count()));
        obj.setAccountNumber(Util.generateAccountNumber());
        obj.setAccountDefault(accountRepository.findByUserCode(userCode).isEmpty());
        obj.setUserCode(userCode);
        obj.setBalance(0.00);

        AccountEntity transferEntity = accountRepository.save(obj);
        return buildResponse(true, Constants.SUCCESS, Constants.USER_CREATED, transferEntity);
    }

    public ApiResponse<AccountEntity> update(AccountEntity obj) {
        AccountEntity transferEntity = accountRepository.save(obj);
        return buildResponse(true, Constants.SUCCESS, Constants.USER_CREATED, transferEntity);
    }

    private ApiResponse<AccountEntity> buildResponse(boolean status, String code, String message, AccountEntity entity) {
        ApiResponse<AccountEntity> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(status);
        apiResponse.setCode(code);
        apiResponse.setMessage(message);
        apiResponse.setData(entity);
        return apiResponse;
    }

}
