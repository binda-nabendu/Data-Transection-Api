package org.example.datatransectionapi.model;

import org.springframework.stereotype.Component;

@Component
public class LoanLedger {
    private int memberId;
    private int debitAmount;
    private int creditAmount;
    private String voucherNo;

    public LoanLedger() {
    }

    public LoanLedger(int memberId, int debitAmount, int creditAmount, String voucherNo) {
        this.memberId = memberId;
        this.debitAmount = debitAmount;
        this.creditAmount = creditAmount;
        this.voucherNo = voucherNo;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
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

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }
}
