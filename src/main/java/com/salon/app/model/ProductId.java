package com.salon.app.model;

public class ProductId {
	private int id;
    private String title;
    private Double price;
    private String quantity;
    private String type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "ProductId [id=" + id + ", title=" + title + ", price=" + price + ", quantity=" + quantity + ", type="
				+ type + "]";
	}
    
    
	
	
}
