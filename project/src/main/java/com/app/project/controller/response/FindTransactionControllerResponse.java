package com.app.project.controller.response;

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
public class FindTransactionControllerResponse {
    private String email;
    private String phone;
    private Date orderDate;
    private String status;
    private int totalPayment;
    private List<Identity> identities;

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
