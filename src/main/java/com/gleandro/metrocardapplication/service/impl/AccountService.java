package com.gleandro.metrocardapplication.service.impl;

import com.gleandro.metrocardapplication.entity.AccountEntity;
import com.gleandro.metrocardapplication.entity.RechargeAccountEntity;
import com.gleandro.metrocardapplication.entity.TransferEntity;
import com.gleandro.metrocardapplication.repository.AccountRepository;
import com.gleandro.metrocardapplication.repository.RechargeAccountRepository;
import com.gleandro.metrocardapplication.repository.TransferRepository;
import com.gleandro.metrocardapplication.service.IAccountService;
import com.gleandro.metrocardapplication.util.ApiResponse;
import com.gleandro.metrocardapplication.util.Constants;
import com.gleandro.metrocardapplication.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    private final RechargeAccountRepository rechargeAccountRepository;

    private final TransferRepository transferRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, RechargeAccountRepository rechargeAccountRepository, TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.rechargeAccountRepository = rechargeAccountRepository;
        this.transferRepository = transferRepository;
    }

    public List<TransferEntity> getAllTrasnsaction(String accountCode) {
        List<TransferEntity> listRecharge = getRechargeAccounts(accountCode, null);
        List<TransferEntity> listTransfer = getTrasnfers(accountCode);
        List<TransferEntity> combinedList = new ArrayList<>();
        combinedList.addAll(listRecharge);
        combinedList.addAll(listTransfer);
        return combinedList;
    }

    public List<AccountEntity> getAccounts(String userCode) {
        return accountRepository.findByUserCode(userCode);
    }

    public List<TransferEntity> getRechargeAccounts(String accountCode, String userCode) {
        List<RechargeAccountEntity> listResponse = rechargeAccountRepository.findByCodeAccount(accountCode);
        return listResponse.stream().map(rechargeAccountEntity -> {
            TransferEntity transferEntity = new TransferEntity();
            transferEntity.setUserCode(rechargeAccountEntity.getUserCode());
            transferEntity.setAccountCodeTo(rechargeAccountEntity.getAccountEntity().getAccountCode());
            transferEntity.setAccountCodeFrom(rechargeAccountEntity.getAccountEntity().getAccountCode());
            transferEntity.setAccountNumberFrom(rechargeAccountEntity.getAccountEntity().getAccountNumber());
            transferEntity.setAccountNumberTo(rechargeAccountEntity.getAccountEntity().getAccountNumber());
            transferEntity.setAmount(rechargeAccountEntity.getAmount());
            transferEntity.setCreatedDate(rechargeAccountEntity.getCreatedDate());
            transferEntity.setTypeTransaction("Recharge");
            return transferEntity;
        }).toList();
    }

    public List<TransferEntity> getTrasnfers(String filter) {
        List<TransferEntity> listResponse = transferRepository.getTransferByFilter(filter);
        listResponse = listResponse.stream().map(t -> {
            TransferEntity transferEntity = new TransferEntity();
            transferEntity.setUserCode(t.getUserCode());
            transferEntity.setAccountCodeTo(t.getAccountEntityTo().getAccountCode());
            transferEntity.setAccountCodeFrom(t.getAccountEntityFrom().getAccountCode());
            transferEntity.setAccountNumberFrom(t.getAccountEntityFrom().getAccountNumber());
            transferEntity.setAccountNumberTo(t.getAccountEntityTo().getAccountNumber());
            transferEntity.setAmount(t.getAmount());
            transferEntity.setCreatedDate(t.getCreatedDate());
            transferEntity.setTypeTransaction("Transfer");
            return transferEntity;
        }).toList();
        return listResponse;
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
