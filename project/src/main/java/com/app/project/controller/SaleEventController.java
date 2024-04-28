package com.app.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.project.constant.ApiPath;
import com.app.project.controller.request.CreateSaleEventControllerRequest;
import com.app.project.service.SaleEventService;
import com.app.project.serviceImpl.request.CreateSaleEventServiceRequest;

import io.beanmapper.config.BeanMapperBuilder;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = ApiPath.SALE_EVENTS, 
    consumes = MediaType.APPLICATION_JSON_VALUE, 
    produces = MediaType.APPLICATION_JSON_VALUE)
public class SaleEventController {

    @Autowired
    private SaleEventService service;
    
    @PostMapping
    private ResponseEntity<String> create(@Valid @RequestBody CreateSaleEventControllerRequest request) {
        service.create(new BeanMapperBuilder().build().map(request, CreateSaleEventServiceRequest.class));
        return ResponseEntity.created(null).build();
    }
}
