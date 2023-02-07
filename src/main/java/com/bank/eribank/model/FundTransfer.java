package com.bank.eribank.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FundTransfer {


    public FundTransfer(){}

    public FundTransfer(String transactionID, String name, String sourceAccount, String targetAccount, String amount, String dateTime) {
        this.transactionID = transactionID;
        this.name = name;
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    String transactionID;

    String name;

    String sourceAccount;

    String targetAccount;

    String amount;

    String dateTime;

}
