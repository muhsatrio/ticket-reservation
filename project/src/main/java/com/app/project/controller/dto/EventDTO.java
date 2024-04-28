package com.app.project.controller.dto;

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
public class EventDTO {
    private String id;
    private String name;
    private Date eventDate;
    private int qty;
    private List<SaleEvent> saleEvents;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaleEvent {
        private String id;
        private Date startDate;
        private Date endDate;
        private int qty;
        private int price;
    }
}
