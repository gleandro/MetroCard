package com.gleandro.metrocardapplication.controller;

import com.gleandro.metrocardapplication.entity.TransferEntity;
import com.gleandro.metrocardapplication.service.impl.TransferService;
import com.gleandro.metrocardapplication.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
@CrossOrigin(origins = "*")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @GetMapping
    public List<TransferEntity> getTrasnfers(@RequestParam(required = false) String filter) {
        return transferService.getTrasnfers(filter);
    }

    @PostMapping
    public ApiResponse<TransferEntity> createTransfer(@RequestBody TransferEntity transferEntity) {
        return transferService.add(transferEntity);
    }


}
