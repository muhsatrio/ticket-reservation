package com.app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.project.repository.entity.SaleEvent;

public interface SaleEventRepository extends JpaRepository<SaleEvent, String> {
    List<SaleEvent> findByEventIdAndDeletedAtIsNull(String eventId);
    SaleEvent findByIdAndDeletedAtIsNull(String id);
}
