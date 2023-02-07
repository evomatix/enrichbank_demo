package com.bank.eribank.controllers;

import com.bank.eribank.data.FundsStore;
import com.bank.eribank.model.FundTransfer;
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
        FundsStore.getInstance().addFundTransfer(fundTransfer);
        return status(CREATED).build();

    }
}
