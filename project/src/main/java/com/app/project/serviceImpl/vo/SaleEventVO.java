package com.app.project.serviceImpl.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleEventVO {
    private String id;
    private int qty;
    private int price;
    private Date startDate;
    private Date endDate;
}
