package com.app.project.service;

import java.util.List;

import com.app.project.serviceImpl.vo.EventVO;

public interface EventService {
    void seed(int number);
    List<EventVO> get();
    EventVO find(String id);
}
