package com.app.project.serviceImpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.app.project.repository.EventRepository;
import com.app.project.repository.dto.EventDTO;
import com.app.project.repository.entity.Event;
import com.app.project.service.EventService;
import com.app.project.serviceImpl.vo.EventVO;
import com.app.project.serviceImpl.vo.SaleEventVO;

import io.beanmapper.config.BeanMapperBuilder;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repository;

    @Override
    public void seed(int number) {
        List<Event> events = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < number; i++) {
            LocalDate date = LocalDate.of(2025, random.nextInt(11) + 1, random.nextInt(27) + 1);
            events.add(
                Event.builder()
                    .name("Event " + random.nextInt(1000))
                    .eventDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                    .qty(100000)
                    .build()
                );
        }

        repository.saveAll(events);
    }

    @Override
    public List<EventVO> get() {
        var events = repository.findAllSummary();

        List<EventVO> eventVOs = new ArrayList<>();

        var tempId = "";
        var lastIndex = -1;

        for (EventDTO eventDTO : events) {
            if (!tempId.equals(eventDTO.getId())) {
                var eventVO = new BeanMapperBuilder().build().map(eventDTO, EventVO.class);
                eventVOs.add(eventVO);
                tempId = eventDTO.getId();
                lastIndex++;
            }
            if (Objects.nonNull(eventDTO.getSaleEventId())) {
                eventVOs.get(lastIndex).getSaleEvents().add(SaleEventVO.builder()
                    .id(eventDTO.getSaleEventId())
                    .qty(eventDTO.getSaleEventQty())
                    .price(eventDTO.getSaleEventPrice())
                    .startDate(eventDTO.getStartDate())
                    .endDate(eventDTO.getEndDate())
                    .build());
            }
        }

        return eventVOs;
    }

    @Override
    public EventVO find(String id) {
        var result = repository.findByIdAndDeletedAtIsNull(id);
        
        if (Objects.isNull(result)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "event is not found");
        }

        return new BeanMapperBuilder().build().map(result, EventVO.class);
    }
}
