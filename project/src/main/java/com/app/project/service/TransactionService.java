package com.app.project.service;

import com.app.project.serviceImpl.request.CreateTransactionServiceRequest;
import com.app.project.serviceImpl.request.UpdateTransactionServiceRequest;
import com.app.project.serviceImpl.response.CreateTransactionServiceResponse;
import com.app.project.serviceImpl.response.FindTransactionServiceResponse;

public interface TransactionService {
    CreateTransactionServiceResponse create(CreateTransactionServiceRequest request);
    void update(UpdateTransactionServiceRequest request, String id);
    FindTransactionServiceResponse find(String id);
}
