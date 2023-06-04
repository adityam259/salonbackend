package com.salon.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salon.app.model.TrustedProduct;

public interface TrustedProductRepository  extends JpaRepository<TrustedProduct, Long> {

}
