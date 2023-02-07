package com.bank.eribank.data;

import com.bank.eribank.model.FundTransfer;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class FundsStore {

    private static final FundsStore instance = new FundsStore();

    private FundsStore(){
        fundTransfers = new HashMap<String,FundTransfer>();
        FundTransfer transfer = new FundTransfer();
        transfer.setTransactionID("1234567");
        transfer.setName("Mahinda Rajapakshe");
        transfer.setDateTime("11th of November 2023");
        transfer.setTargetAccount("T123456");
        transfer.setSourceAccount("S123456");
        fundTransfers.put("1234567",transfer);
    }

    public static FundsStore getInstance() {
        return instance;
    }

    private HashMap<String,FundTransfer> fundTransfers;


    public void  addFundTransfer(FundTransfer fundTransfer){
        if(fundTransfers.containsKey(fundTransfer.getTransactionID())){
            fundTransfers.remove(fundTransfer.getTransactionID());
        }
        fundTransfers.put(fundTransfer.getTransactionID(),fundTransfer);
    }


    public FundTransfer  getFundTransfer(String transactionID){
        if(fundTransfers.containsKey(transactionID)){
           return fundTransfers.get(transactionID);
        }else{
            return null;
        }

    }
}