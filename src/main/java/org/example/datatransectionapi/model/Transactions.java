package org.example.datatransectionapi.model;

import org.springframework.stereotype.Component;

@Component
public class Transactions {
    private String voucheNo;
    private int debitAmount;
    private int creditAmount;
    private String ledgerName;
    private int branchId;

    public Transactions() {
    }

    public Transactions(String voucheNo, int debitAmount, int creditAmount, String ledgerName, int branchId) {
        this.voucheNo = voucheNo;
        this.debitAmount = debitAmount;
        this.creditAmount = creditAmount;
        this.ledgerName = ledgerName;
        this.branchId = branchId;
    }

    public String getVoucheNo() {
        return voucheNo;
    }

    public void setVoucheNo(String voucheNo) {
        this.voucheNo = voucheNo;
    }

    public int getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(int debitAmount) {
        this.debitAmount = debitAmount;
    }

    public int getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(int creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
}
