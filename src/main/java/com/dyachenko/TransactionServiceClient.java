package com.dyachenko;

import com.dyachenko.api.AccountsState;
import com.dyachenko.api.Transaction;
import com.dyachenko.api.TransactionService;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public class TransactionServiceClient implements TransactionService {
    private static final TransactionService INSTANCE = new TransactionServiceClient();

    public static TransactionService getInstance() {
        return INSTANCE;
    }

    private static final String REST_URI = "http://localhost:8090/rest/transaction";
    private static final Client CLIENT = ClientBuilder.newClient();

    @Override
    public AccountsState addTransaction(Transaction transaction) {
        return CLIENT.target(REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(transaction, MediaType.APPLICATION_JSON), AccountsState.class);
    }
}
