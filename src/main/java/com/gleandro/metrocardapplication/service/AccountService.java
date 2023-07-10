package com.gleandro.metrocardapplication.service;

import com.gleandro.metrocardapplication.entity.AccountEntity;
import com.gleandro.metrocardapplication.repository.AccountRepository;
import com.gleandro.metrocardapplication.util.ApiResponse;
import com.gleandro.metrocardapplication.util.Constants;
import com.gleandro.metrocardapplication.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<AccountEntity> getAccounts(String userCode) {
        return accountRepository.findByUserCode(userCode);
    }


    public ApiResponse<AccountEntity> getAccountByCodeOrNumber(String filter) {
        Optional<AccountEntity> accountEntity = accountRepository.getByAccountCode(filter.replace(" ", ""));
        if (accountEntity.isEmpty()) {
            return buildResponse(false, Constants.ERROR, Constants.ACCOUNT_NOT_FOUND, null);
        }

        return buildResponse(true, Constants.SUCCESS, "", accountEntity.get());
    }

    public ApiResponse<AccountEntity> add(String userCode) {

        AccountEntity obj = new AccountEntity();
        obj.setAccountCode(Constants.ACCOUNT_PREFIX + Util.formatCodeNumber(accountRepository.count()));
        obj.setAccountNumber(Util.generateAccountNumber());
        obj.setAccountDefault(this.getAccounts(userCode).isEmpty());
        obj.setUserCode(userCode);
        obj.setBalance(0.00);

        AccountEntity transferEntity = accountRepository.save(obj);
        return buildResponse(true, Constants.SUCCESS, Constants.ACCOUNT_CREATED, transferEntity);
    }

    public ApiResponse<AccountEntity> update(AccountEntity obj) {
        AccountEntity transferEntity = accountRepository.save(obj);
        return buildResponse(true, Constants.SUCCESS, Constants.ACCOUNT_UPDATE, transferEntity);
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
