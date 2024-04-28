package com.app.project.repository.dto;

import java.util.Date;

public interface TransactionDTO {
    String getEmail();
    String getPhone();
    String getIdentityName();
    String getIdentityNumber();
    String getIdentityType();
    Date getOrderDate();
    String getStatus();
    int getTotalPayment();
}
