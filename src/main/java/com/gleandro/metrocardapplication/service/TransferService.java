package com.gleandro.metrocardapplication.service;

import com.gleandro.metrocardapplication.entity.AccountEntity;
import com.gleandro.metrocardapplication.entity.TransferEntity;
import com.gleandro.metrocardapplication.entity.UserEntity;
import com.gleandro.metrocardapplication.repository.TransferRepository;
import com.gleandro.metrocardapplication.util.ApiResponse;
import com.gleandro.metrocardapplication.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    public List<TransferEntity> getTrasnfers(String filter) {
        List<TransferEntity> listResponse = transferRepository.getTransferByFilter(filter);
        listResponse = listResponse.stream().map(transferEntity -> {
            transferEntity.setAccountNumberFrom(transferEntity.getAccountEntityFrom().getAccountNumber());
            transferEntity.setAccountNumberTo(transferEntity.getAccountEntityTo().getAccountNumber());
            return transferEntity;
        }).toList();

        return listResponse;
    }

    @Transactional
    public ApiResponse<TransferEntity> add(TransferEntity obj) {
        obj.setCreatedDate(LocalDateTime.now(ZoneId.of(Constants.TIME_ZONE_DEFAULT)));

        ApiResponse<UserEntity> userEntity = userService.getUserByCode(obj.getUserCode());
        if (Boolean.FALSE.equals(userEntity.getSuccess())) {
            return buildResponse(false, Constants.ERROR, Constants.USER_NOT_FOUND, null);
        }

        ApiResponse<AccountEntity> accountFrom = accountService.getAccountByCodeOrNumber(obj.getAccountCodeFrom());
        if (Boolean.FALSE.equals(accountFrom.getSuccess())) {
            return buildResponse(false, Constants.ERROR, Constants.ACCOUNT_NOT_FOUND, null);
        }

        ApiResponse<AccountEntity> accountTo = accountService.getAccountByCodeOrNumber(obj.getAccountCodeTo());
        if (Boolean.FALSE.equals(accountTo.getSuccess())) {
            return buildResponse(false, Constants.ERROR, Constants.ACCOUNT_NOT_FOUND, null);
        }

        AccountEntity accountFromEntity = accountFrom.getData();
        AccountEntity accountToEntity = accountTo.getData();

        accountFromEntity.setBalance(accountFromEntity.getBalance() - obj.getAmount());
        accountToEntity.setBalance(accountToEntity.getBalance() + obj.getAmount());

        if (accountFromEntity.getBalance() >= 0 && accountToEntity.getBalance() >= 0) {
            accountService.update(accountFromEntity);
            accountService.update(accountToEntity);
        } else {
            return buildResponse(false, Constants.ERROR, Constants.TRANSFER_ERROR, null);
        }

        obj.setAccountCodeTo(accountToEntity.getAccountCode());
        obj.setAccountCodeFrom(accountFromEntity.getAccountCode());

        TransferEntity transferEntity = transferRepository.save(obj);
        obj.setAccountNumberFrom(accountFromEntity.getAccountNumber());
        obj.setAccountNumberTo(accountToEntity.getAccountNumber());
        return buildResponse(true, Constants.SUCCESS, Constants.TRANSFER_CREATED, transferEntity);
    }

    private ApiResponse<TransferEntity> buildResponse(boolean status, String code, String message, TransferEntity entity) {
        ApiResponse<TransferEntity> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(status);
        apiResponse.setCode(code);
        apiResponse.setMessage(message);
        apiResponse.setData(entity);
        return apiResponse;
    }

}
