package com.bank.eribank.controllers;

import com.bank.eribank.data.FundsStore;
import com.bank.eribank.model.FundTransfer;
import com.bank.eribank.mq.MQHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;

@RequestMapping("/funds")
@RequiredArgsConstructor
@RestController
public class FundController {
    ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/transfers")
    List<FundTransfer> findAll() {
        return new ArrayList<FundTransfer>(FundsStore.getInstance().getFundTransfers().values());
    }

    @GetMapping("/transfer/{id}")
    FundTransfer findOne(@PathVariable String id) {
        return FundsStore.getInstance().getFundTransfer(id);
    }

    @PostMapping("/transfer")
    ResponseEntity<Void> insert(@RequestBody FundTransfer fundTransfer) {
        String message;
        FundsStore.getInstance().addFundTransfer(fundTransfer);
        try {
            message=mapper.writeValueAsString(fundTransfer);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to parse the JSON value",e);
        }
        MQHandler.sendMessage("localhost",message);
        return status(CREATED).build();

    }
}
