package com.salon.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.app.model.BookedTimeSlots;

public interface BookedTimeSlotsRepo  extends JpaRepository<BookedTimeSlots, Long> {

}
