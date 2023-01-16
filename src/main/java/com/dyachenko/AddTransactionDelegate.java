package com.dyachenko;

import com.dyachenko.api.Transaction;
import com.dyachenko.api.TransactionService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class AddTransactionDelegate implements JavaDelegate {
    private static final TransactionService TRANSACTION_SERVICE = TransactionServiceClient.getInstance();
    private static final String OUTCOME_ACCOUNT_ID = "outcomeAccountId";
    private static final String INCOME_ACCOUNT_ID = "incomeAccountId";
    private static final String SUM = "sum";

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long outcomeAccountId = (Long) delegateExecution.getVariable(OUTCOME_ACCOUNT_ID);
        Long incomeAccountId = (Long) delegateExecution.getVariable(INCOME_ACCOUNT_ID);
        Long sum = (Long) delegateExecution.getVariable(SUM);

        Transaction transaction = new Transaction();
        transaction.setOutcomeAccountId(outcomeAccountId);
        transaction.setIncomeAccountId(incomeAccountId);
        transaction.setSum(sum);

        TRANSACTION_SERVICE.addTransaction(transaction);
    }
}
