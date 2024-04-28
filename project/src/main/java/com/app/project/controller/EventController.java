package com.app.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.project.constant.ApiPath;
import com.app.project.controller.dto.EventDTO;
import com.app.project.service.EventService;
import com.app.project.serviceImpl.vo.EventVO;

import io.beanmapper.config.BeanMapperBuilder;

@RestController
@RequestMapping(path = ApiPath.EVENTS, 
    consumes = MediaType.APPLICATION_JSON_VALUE, 
    produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {

    @Autowired
    private EventService service;
    
    @PostMapping
    private ResponseEntity<String> seed(@RequestParam("number") int number) {

        service.seed(number);

        return ResponseEntity.created(null).build();
    }

    @GetMapping
    private ResponseEntity<List<EventDTO>> get() {
        List<EventVO> eventVOs = service.get();

        return ResponseEntity.ok(new BeanMapperBuilder().build().map(eventVOs, EventDTO.class));
    }
}
