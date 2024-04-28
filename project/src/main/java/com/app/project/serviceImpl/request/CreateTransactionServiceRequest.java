package com.app.project.serviceImpl.request;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionServiceRequest {
    private String email;
    private String phone;
    private String saleEventId;
    private List<Identity> identities;
    private Date orderDate;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Identity {
        private String name;
        private String identityNumber;
        private String identityType;
    }
}
