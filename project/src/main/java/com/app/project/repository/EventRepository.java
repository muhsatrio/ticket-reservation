package com.app.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.project.repository.dto.EventDTO;
import com.app.project.repository.entity.Event;

public interface EventRepository extends JpaRepository<Event, String> {
    List<Event> findAllByDeletedAtIsNullOrderByEventDateDesc();
    Event findByIdAndDeletedAtIsNull(String id);

    @Query(value = "SELECT e.id, e.name, e.event_date as eventDate, e.qty, se.id as saleEventId, se.qty as saleEventQty, se.price as saleEventPrice,  se.start_date as startDate, se.end_date as endDate FROM event e LEFT OUTER JOIN sale_event se ON e.id = se.event_id WHERE e.deleted_at IS NULL AND se.deleted_at IS NULL", nativeQuery = true)
    List<EventDTO> findAllSummary();
}
