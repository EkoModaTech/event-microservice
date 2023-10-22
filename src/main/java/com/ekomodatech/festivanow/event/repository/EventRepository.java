package com.ekomodatech.festivanow.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ekomodatech.festivanow.event.entity.Event;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByVisibilityFalse();
    List<Event> findByCreatedByOrVisibilityFalse(String createdBy);
    List<Event> findByCreatedBy(String createdBy);
}
