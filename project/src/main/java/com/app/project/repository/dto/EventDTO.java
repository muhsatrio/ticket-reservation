package com.app.project.repository.dto;

import java.util.Date;

public interface EventDTO {
    String getId();
    String getName();
    Date getEventDate();
    int getQty();
    String getSaleEventId();
    int getSaleEventQty();
    int getSaleEventPrice();
    Date getStartDate();
    Date getEndDate();
}
