package com.app.project.serviceImpl;

import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.app.project.repository.SaleEventRepository;
import com.app.project.repository.entity.SaleEvent;
import com.app.project.service.EventService;
import com.app.project.service.SaleEventService;
import com.app.project.serviceImpl.request.CreateSaleEventServiceRequest;
import com.app.project.serviceImpl.vo.SaleEventVO;

import io.beanmapper.config.BeanMapperBuilder;

@Service
public class SaleEventServiceImpl implements SaleEventService {

    @Autowired
    private EventService eventService;

    @Autowired
    private SaleEventRepository repository;

    @Override
    public void create(CreateSaleEventServiceRequest request) {
        validate(request);
        var eventVO = eventService.find(request.getEventId());

        if (request.getStartDate().after(eventVO.getEventDate()) || request.getEndDate().after(eventVO.getEventDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "request.startDate and request.endDate should be between eventDate");
        }

        var saleEvents = repository.findByEventIdAndDeletedAtIsNull(request.getEventId());

        var totalQty = 0;

        for (int i = 0; i < saleEvents.size(); i++) {
            totalQty+=saleEvents.get(i).getQty();
        }

        if (totalQty + request.getQty() > eventVO.getQty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "request.qty has been reached more than maximum qty");
        }

        var saleEvent = new BeanMapperBuilder().build().map(request, SaleEvent.class);

        repository.save(saleEvent);
    }

    private void validate(CreateSaleEventServiceRequest request) {
        if (Strings.isBlank(request.getEventId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "eventId must not blank");
        }
        if (Objects.isNull(request.getStartDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "startDate must not blank");
        }
        if (Objects.isNull(request.getEndDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "endDate must not blank");
        }
        if (Objects.isNull(request.getQty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qty must not blank");
        }
        if (request.getQty() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qty must be positive");
        }
        if (Objects.isNull(request.getPrice())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "price must not blank");
        }
        if (request.getPrice() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "price must be positive");
        }
    }

    @Override
    public SaleEventVO find(String id) {
        var saleEvent = repository.findByIdAndDeletedAtIsNull(id);

        if (Objects.isNull(saleEvent)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "saleEvent not found");
        }

        return new BeanMapperBuilder().build().map(saleEvent, SaleEventVO.class);
    }
    
}
