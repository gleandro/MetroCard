package com.gleandro.metrocardapplication.service.impl;

import com.gleandro.metrocardapplication.entity.UserEntity;
import com.gleandro.metrocardapplication.repository.UserRepository;
import com.gleandro.metrocardapplication.util.ApiResponse;
import com.gleandro.metrocardapplication.util.Constants;
import com.gleandro.metrocardapplication.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ApiResponse<UserEntity> add(UserEntity user) {
        user.setUserCode(Constants.USER_PREFIX + Util.formatCodeNumber(userRepository.count()));

        ApiResponse<UserEntity> userOptional = this.getUserByDNI(user.getDni());
        if (Boolean.TRUE.equals(userOptional.getSuccess())) {
            return buildResponse(false, Constants.ERROR, Constants.USER_DUPLICATED, null);
        }

        UserEntity userEntity = userRepository.save(user);
        return buildResponse(true, Constants.SUCCESS, Constants.USER_CREATED, userEntity);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public ApiResponse<UserEntity> getUserByDNI(String dni) {
        Optional<UserEntity> userOptional = userRepository.getByDni(dni);
        return userOptional.isEmpty() ? buildResponse(false, Constants.ERROR, Constants.USER_NOT_FOUND, null)
                : buildResponse(true, Constants.SUCCESS, "", userOptional.get());
    }

    public ApiResponse<UserEntity> getUserByCode(String code) {
        Optional<UserEntity> userOptional = userRepository.getByUserCode(code);
        return userOptional.isEmpty() ? buildResponse(false, Constants.ERROR, Constants.USER_NOT_FOUND, null)
                : buildResponse(true, Constants.SUCCESS, "", userOptional.get());
    }

    public ApiResponse<UserEntity> updateUser(UserEntity userEntity) {
        ApiResponse<UserEntity> userEntityApiResponse = getUserByCode(userEntity.getUserCode());
        if (Boolean.FALSE.equals(userEntityApiResponse.getSuccess())) {
            return buildResponse(false, Constants.ERROR, Constants.USER_NOT_FOUND, null);
        }

        UserEntity user = userEntityApiResponse.getData();
        user.setName(userEntity.getName() == null ? user.getName() : userEntity.getName());
        user.setLastName(userEntity.getLastName() == null ? user.getLastName() : userEntity.getLastName());
        user.setAddress(userEntity.getAddress() == null ? user.getAddress() : userEntity.getAddress());
        user.setEmail(userEntity.getEmail() == null ? user.getEmail() : userEntity.getEmail());
        user.setPassword(userEntity.getPassword() == null ? user.getPassword() : userEntity.getPassword());
        user.setDateOfBirth(userEntity.getDateOfBirth() == null ? user.getDateOfBirth() : userEntity.getDateOfBirth());
        userRepository.save(user);

        return buildResponse(true, Constants.SUCCESS, Constants.USER_UPDATE, userEntity);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public ApiResponse<UserEntity> login(UserEntity user) {
        UserEntity userEntity;
        try {
            ApiResponse<UserEntity> userOptional = this.getUserByDNI(user.getDni());
            if (Boolean.FALSE.equals(userOptional.getSuccess())) {
                return buildResponse(false, Constants.ERROR, Constants.USER_NOT_FOUND, null);
            }

            userEntity = userOptional.getData();
            if (!userEntity.getPassword().equals(user.getPassword())) {
                return buildResponse(false, Constants.ERROR, Constants.USER_PASSWORD_INCORRECT, null);
            }
        } catch (Exception e) {
            return buildResponse(false, Constants.ERROR, e.getMessage(), null);
        }

        return buildResponse(true, Constants.SUCCESS, Constants.USER_SUCCESS_LOGIN, userEntity);
    }

    private ApiResponse<UserEntity> buildResponse(boolean status, String code, String message, UserEntity userEntity) {
        ApiResponse<UserEntity> apiResponse = new ApiResponse<>();
        apiResponse.setSuccess(status);
        apiResponse.setCode(code);
        apiResponse.setMessage(message);
        apiResponse.setData(userEntity);
        return apiResponse;
    }

}
