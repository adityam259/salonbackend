package com.salon.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cancelation_master")
public class CancelationInstruction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cancelationInstruction;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCancelationInstruction() {
		return cancelationInstruction;
	}
	public void setCancelationInstruction(String cancelationInstruction) {
		this.cancelationInstruction = cancelationInstruction;
	}
	
	
	
	

}
