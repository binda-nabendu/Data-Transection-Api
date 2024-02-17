package org.example.datatransectionapi.model;

import org.springframework.stereotype.Component;

@Component
public class Disburse {
    private int memberId;
    private int amount;
    private int branchId;

    public Disburse(){

    }

    public Disburse(int memberId, int amount, int branchId) {
        this.memberId = memberId;
        this.amount = amount;
        this.branchId = branchId;
    }
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
}
