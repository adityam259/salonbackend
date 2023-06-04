package com.salon.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.app.model.TimeSlot;

public interface TimeSlotRepo  extends JpaRepository<TimeSlot, Long> {

}
