package com.gleandro.metrocardapplication.controller;

import com.gleandro.metrocardapplication.entity.TransferEntity;
import com.gleandro.metrocardapplication.service.TransferService;
import com.gleandro.metrocardapplication.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
@CrossOrigin(origins = "*")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping
    public ApiResponse<TransferEntity> createTransfer(@RequestBody TransferEntity transferEntity) {
        return transferService.add(transferEntity);
    }


}
