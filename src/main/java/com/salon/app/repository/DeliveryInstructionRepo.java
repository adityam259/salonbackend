package com.salon.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.app.model.CategoryListData;
import com.salon.app.model.DeliveryInstruction;

public interface DeliveryInstructionRepo extends JpaRepository<DeliveryInstruction, Long> {

}
