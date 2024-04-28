package com.app.project.serviceImpl.response;

import java.util.ArrayList;
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
public class FindTransactionServiceResponse {
    private String email;
    private String phone;
    private Date orderDate;
    private String status;
    private int totalPayment;

    @Builder.Default
    private List<Identity> identities = new ArrayList<>();

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
