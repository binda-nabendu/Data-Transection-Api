package org.example.datatransectionapi.dao;

import org.example.datatransectionapi.model.Disburse;
import org.example.datatransectionapi.model.LoanLedger;
import org.example.datatransectionapi.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
@Repository
public class JDBCDao {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public JDBCDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertDisburse(Disburse disburse){
        String disburseInsertQuery = " insert into Disburse " +
                "(memberId,amount, branchId) " +
                "values (?,?,?)";
        jdbcTemplate.update(disburseInsertQuery, disburse.getMemberId(), disburse.getAmount(), disburse.getBranchId());
    }
    public void insertLoanLedger(LoanLedger loanLedger){
        String loanLedgerInsertQuery = "insert into LoanLedger " +
                "(memberId, debtAmount, creditAmount, voucherNo) " +
                "values(?,?,?,?)";
        jdbcTemplate.update(loanLedgerInsertQuery, loanLedger.getMemberId(),
                loanLedger.getDebitAmount(), loanLedger.getCreditAmount(),
                loanLedger.getVoucherNo());
    }
    public void insertTransactions(Transactions transactions){
        String transactionInsertQuery = "insert into Transactions " +
                "(voucheNo, debitAmount, creditAmount, ledgerName, branchId) " +
                "values(?,?,?,?,?)";
        jdbcTemplate.update(transactionInsertQuery,
                transactions.getVoucheNo(), transactions.getDebitAmount(),
                transactions.getCreditAmount(), transactions.getLedgerName(),
                transactions.getBranchId());
    }
    public void updateDisburseByIdAndAmount(int id, int amount){
        String queryForGetBranchId = "select branchId from Disburse where memberId = " + id;
        int branchId = Optional.ofNullable(jdbcTemplate.queryForObject(queryForGetBranchId, Integer.class)).orElse(-1);



        String updateDisburseByIdAndAmountQuery = "update Disburse set amount=? where " +
                "memberId=?";
        jdbcTemplate.update(updateDisburseByIdAndAmountQuery, amount, id);

        //business logic set impaction of other table

        //second table update
        String updateLoanLedgerAccordingToDisburse = "update LoanLedger set debtAmount=? " +
                "where memberId=?";

        jdbcTemplate.update(updateLoanLedgerAccordingToDisburse, amount, id);

        //third table update
        int newAmountOfBranch = getNewAmount(branchId);

        updateTransactionsByBranchIdAndNewAmount(branchId, newAmountOfBranch);
    }
    public void deleteUser(int memberId, int branchId){
//        String queryForGetBranchId = "select branchId from Disburse where memberId = " + memberId;
//        int branchId = Optional.ofNullable(jdbcTemplate.queryForObject(queryForGetBranchId, Integer.class)).orElse(-1);

        String queryForDeleteUserFromLoanLedger = "delete from LoanLedger where memberId=?";
        jdbcTemplate.update(queryForDeleteUserFromLoanLedger, memberId);
        String queryForDeleteUserFromDisburse = "delete from Disburse where memberId=?";
        jdbcTemplate.update(queryForDeleteUserFromDisburse, memberId);

        String queryForGetTotalUser = "select count(*) from Disburse where branchId = " + branchId;
        int totalUser = Optional.ofNullable(jdbcTemplate.queryForObject(queryForGetTotalUser, Integer.class)).orElse(-1);

        // Now we have to update transactions
        if(totalUser > 1)
            updateTransactionsByBranchIdAndNewAmount(branchId, getNewAmount(branchId));
        else{
            String queryForDeleteBranchFromTransactions = "delete from Transactions where branchId=?";
            jdbcTemplate.update(queryForDeleteBranchFromTransactions, branchId);
        }
    }
    private void updateTransactionsByBranchIdAndNewAmount(int branchId, int newAmountOfBranch) {
        String updateTransactionsCreditAccordingToDisburse = "update Transactions set creditAmount = ? " +
                "where ledgerName = 'Cash' and branchId = ?";
        String updateTransactionsDebitAccordingToDisburse = "update Transactions set debitAmount = ? " +
                "where ledgerName = 'Loan' and branchId = ?";

        jdbcTemplate.update(updateTransactionsCreditAccordingToDisburse, newAmountOfBranch, branchId);
        jdbcTemplate.update(updateTransactionsDebitAccordingToDisburse, newAmountOfBranch, branchId);
    }

    private int getNewAmount(int branchId) {
        String q1 = "select SUM(amount) from Disburse where branchId = '" + branchId + "'";
        return Optional.ofNullable(jdbcTemplate.queryForObject(q1, Integer.class)).orElse(-1);
    }
}
