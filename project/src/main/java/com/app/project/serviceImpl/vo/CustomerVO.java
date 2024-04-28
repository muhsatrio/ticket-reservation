package com.app.project.serviceImpl.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerVO {
    private String transactionId;
    private String name;
    private String identityNumber;
    private String identityType;
}
