package org.example.datatransectionapi.controller;

import org.example.datatransectionapi.dao.JDBCDao;
import org.example.datatransectionapi.model.Disburse;
import org.example.datatransectionapi.model.LoanLedger;
import org.example.datatransectionapi.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DbController {

    private final JDBCDao jdbcDao;

    @Autowired
    public DbController(JDBCDao jdbcDao) {
        this.jdbcDao = jdbcDao;
    }

    @PostMapping("/insertDisburse")
    public void insertDisburse() {
        jdbcDao.insertDisburse(new Disburse(101,5000,100));
        jdbcDao.insertDisburse(new Disburse(102,10000,100));
        jdbcDao.insertDisburse(new Disburse(201,20000,200));
    }

    @PostMapping("/insertLoanLedger")
    public void insertLoanLedger() {
        jdbcDao.insertLoanLedger(new LoanLedger(101,5000,0,"DR-CH-1"));
        jdbcDao.insertLoanLedger(new LoanLedger(102,10000,0,"DR-CH-1"));
        jdbcDao.insertLoanLedger(new LoanLedger(201,5000,0,"DR-CH-1"));
    }

    @PostMapping("/insertTransactions")
    public void insertTransactions() {
        jdbcDao.insertTransactions(new Transactions("DR-CH-1",0,15000,"Cash",100));
        jdbcDao.insertTransactions(new Transactions("DR-CH-1",15000,0,"Loan",100));
        jdbcDao.insertTransactions(new Transactions("DR-CH-1",0,20000,"Cash",200));
        jdbcDao.insertTransactions(new Transactions("DR-CH-1",20000,0,"Loan",200));
    }

    @PostMapping("/updateDisburseByIdAndAmount")
    public void updateDisburseByIdAndAmount() {
        jdbcDao.updateDisburseByIdAndAmount(101, 10000);
        jdbcDao.updateDisburseByIdAndAmount(102, 15000);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser() {
        jdbcDao.deleteUser(201,200);
    }
}

