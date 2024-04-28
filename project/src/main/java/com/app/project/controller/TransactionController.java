package com.app.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.project.constant.ApiPath;
import com.app.project.controller.request.CreateTransactionControllerRequest;
import com.app.project.controller.request.UpdateTransactionControllerRequest;
import com.app.project.controller.response.CreateTransactionControllerResponse;
import com.app.project.controller.response.FindTransactionControllerResponse;
import com.app.project.service.TransactionService;
import com.app.project.serviceImpl.request.CreateTransactionServiceRequest;
import com.app.project.serviceImpl.request.UpdateTransactionServiceRequest;

import io.beanmapper.config.BeanMapperBuilder;

@RestController
@RequestMapping(path = ApiPath.TRANSACTIONS, 
    consumes = MediaType.APPLICATION_JSON_VALUE, 
    produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    private static final String ID = "/{id}";
    
    @Autowired
    private TransactionService service;

    @PostMapping
    private ResponseEntity<CreateTransactionControllerResponse> create(@RequestBody CreateTransactionControllerRequest request) {
        var response = service.create(new BeanMapperBuilder().build().map(request, CreateTransactionServiceRequest.class));

        return ResponseEntity.ok(new BeanMapperBuilder().build().map(response, CreateTransactionControllerResponse.class));
    }

    @PatchMapping(ID)
    private ResponseEntity<String> update(@RequestBody UpdateTransactionControllerRequest request, @PathVariable String id) {
        service.update(new BeanMapperBuilder().build().map(request, UpdateTransactionServiceRequest.class), id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(ID)
    private ResponseEntity<FindTransactionControllerResponse> find(@PathVariable String id) {
        var response = service.find(id);

        return ResponseEntity.ok(new BeanMapperBuilder().build().map(response, FindTransactionControllerResponse.class));
    }
}
