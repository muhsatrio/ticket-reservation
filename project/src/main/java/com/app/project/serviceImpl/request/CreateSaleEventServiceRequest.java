package com.app.project.serviceImpl.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSaleEventServiceRequest {
    private String eventId;
    private Date startDate;
    private Date endDate;
    private int qty;
    private int price;
}
