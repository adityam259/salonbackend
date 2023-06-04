package com.salon.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.app.model.CancelationInstruction;

public interface CancelationInstructionRepo extends JpaRepository<CancelationInstruction, Long> {

}
