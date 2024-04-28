package com.app.project.service;

import com.app.project.serviceImpl.request.CreateSaleEventServiceRequest;
import com.app.project.serviceImpl.vo.SaleEventVO;

public interface SaleEventService {
    void create(CreateSaleEventServiceRequest request);
    SaleEventVO find(String id);
}
