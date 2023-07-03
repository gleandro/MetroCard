package com.gleandro.metrocardapplication.service;

import com.gleandro.metrocardapplication.entity.TransferEntity;
import com.gleandro.metrocardapplication.entity.UserEntity;
import com.gleandro.metrocardapplication.repository.TransferRepository;
import com.gleandro.metrocardapplication.util.ApiResponse;
import com.gleandro.metrocardapplication.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private UserService userService;

    public ApiResponse<TransferEntity> add(TransferEntity obj) {
        ApiResponse<UserEntity> userEntity = userService.getUserByCode(obj.getUserCode());

        if (Boolean.FALSE.equals(userEntity.getSuccess())) {
            return buildResponse(false, Constants.ERROR, Constants.USER_NOT_FOUND, null);
        }

        TransferEntity transferEntity = transferRepository.save(obj);
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
