package com.app.project.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.app.project.constant.IdentityType;
import com.app.project.constant.TransactionStatus;
import com.app.project.repository.TransactionRepository;
import com.app.project.repository.dto.TransactionDTO;
import com.app.project.repository.entity.Transaction;
import com.app.project.service.TransactionService;
import com.app.project.service.CustomerService;
import com.app.project.service.SaleEventService;
import com.app.project.serviceImpl.request.CreateTransactionServiceRequest;
import com.app.project.serviceImpl.request.CreateTransactionServiceRequest.Identity;
import com.app.project.serviceImpl.request.UpdateTransactionServiceRequest;
import com.app.project.serviceImpl.response.CreateTransactionServiceResponse;
import com.app.project.serviceImpl.response.FindTransactionServiceResponse;
import com.app.project.serviceImpl.vo.CustomerVO;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private SaleEventService saleEventService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionRepository repository;

    @Override
    public CreateTransactionServiceResponse create(CreateTransactionServiceRequest request) {
        validate(request);

        var saleEvent = saleEventService.find(request.getSaleEventId());

        var transactions = repository.findBySaleEventIdAndDeletedAtIsNull(request.getSaleEventId());

        if (request.getIdentities().size() + transactions.size() > saleEvent.getQty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order qty has reached the maximum limit");
        }

        var transaction = Transaction.builder()
            .saleEventId(request.getSaleEventId())
            .email(request.getEmail())
            .phone(request.getPhone())
            .status(TransactionStatus.PENDING.name())
            .totalPayment(saleEvent.getPrice() * request.getIdentities().size())
            .build();

        var savedTransaction = repository.save(transaction);

        List<CustomerVO> customerVOs = new ArrayList<>();

        for (Identity identity : request.getIdentities()) {
            customerVOs.add(CustomerVO.builder()
                .transactionId(savedTransaction.getId())
                .identityNumber(identity.getIdentityNumber())
                .identityType(identity.getIdentityType())
                .name(identity.getName())
                .build());
        }

        customerService.creates(customerVOs);

        return CreateTransactionServiceResponse.builder()
            .id(savedTransaction.getId())
            .status(savedTransaction.getStatus())
            .build();
    }

    private void validate(CreateTransactionServiceRequest request) {
        if (Strings.isBlank(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email must not blank");
        }
        if (Strings.isBlank(request.getPhone())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "phone must not blank");
        }
        if (Strings.isBlank(request.getSaleEventId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "saleEventId must not blank");
        }

        for (Identity eachIdentity : request.getIdentities()) {
            if (Strings.isBlank(eachIdentity.getIdentityType())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "identityType must not blank");
            }
            if (!(IdentityType.KTP.name().equals(eachIdentity.getIdentityType()) || IdentityType.PASSPORT.name().equals(eachIdentity.getIdentityType()))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "identityType should be between KTP or PASSPORT");
            }
            if (Strings.isBlank(eachIdentity.getIdentityNumber())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "identityNumber must not blank");
            }
            if (Strings.isBlank(eachIdentity.getName())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "identity.name must not blank");
            }
        }
    }

    @Override
    public void update(UpdateTransactionServiceRequest request, String id) {
        if (Strings.isBlank(request.getStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "status must not blank");
        }

        if (!(TransactionStatus.APPROVED.name().equals(request.getStatus()) || TransactionStatus.REJECTED.name().equals(request.getStatus()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "status should be between APPROVED or REJECTED");
        }

        var transaction = repository.findByIdAndDeletedAtIsNull(id);

        if (Objects.isNull(transaction)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "transaction not found");
        }

        if (!TransactionStatus.PENDING.name().equals(transaction.getStatus())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "transaction was already processed");
        }

        transaction.setStatus(request.getStatus());

        repository.save(transaction);
    }

    @Override
    public FindTransactionServiceResponse find(String id) {
        var transactions = repository.findSummary(id);

        if (transactions.size() < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "transaction not found");
        }

        var response = FindTransactionServiceResponse.builder()
            .email(transactions.get(0).getEmail())
            .phone(transactions.get(0).getPhone())
            .orderDate(transactions.get(0).getOrderDate())
            .status(transactions.get(0).getStatus())
            .totalPayment(transactions.get(0).getTotalPayment())
            .build();

        for (TransactionDTO transactionDTO : transactions) {
            response.getIdentities().add(com.app.project.serviceImpl.response.FindTransactionServiceResponse.Identity.builder()
                .name(transactionDTO.getIdentityName())
                .identityNumber(transactionDTO.getIdentityNumber())
                .identityType(transactionDTO.getIdentityType())
                .build());
        }

        return response;
    }
}
