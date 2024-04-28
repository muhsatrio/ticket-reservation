package com.app.project.serviceImpl.vo;

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
public class EventVO {
    private String id;
    private String name;
    private Date eventDate;
    private int qty;
    private int price;

    @Builder.Default
    private List<SaleEventVO> saleEvents = new ArrayList<>();
}
