package com.gleandro.metrocardapplication.service;

import com.gleandro.metrocardapplication.entity.AccountEntity;
import com.gleandro.metrocardapplication.entity.TransferEntity;
import com.gleandro.metrocardapplication.util.ApiResponse;

import java.util.List;

public interface IAccountService {

    List<TransferEntity> getAllTrasnsaction(String accountCode);

    List<AccountEntity> getAccounts(String userCode);

    ApiResponse<AccountEntity> getAccountByCodeOrNumber(String filter);

    ApiResponse<AccountEntity> add(String userCode);

    ApiResponse<AccountEntity> update(AccountEntity obj);


}
