package com.mungyang.dto;

public class ProductDTO {
	
	private int id; // 상품 번호 (자동 생성)
	private String name; // 상품명
	private int price; // 가격
	private int categoryId; // 상품 분류
	private String mainPhoto; // 사진1 (필수)
	private String subPhoto; // 사진2
	private int sales; // 판매량 (주문 기능 구현할 때 +수량 되도록 설정하기)
	private String insertDate; // 등록 일자 (자동 생성)
	
	public ProductDTO(int id, String name, int price, int categoryId, String mainPhoto, String insertDate) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.categoryId = categoryId;
		this.mainPhoto = mainPhoto;
		this.insertDate = insertDate;
	}
	
	public ProductDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getMainPhoto() {
		return mainPhoto;
	}

	public void setMainPhoto(String mainPhoto) {
		this.mainPhoto = mainPhoto;
	}

	public String getSubPhoto() {
		return subPhoto;
	}

	public void setSubPhoto(String subPhoto) {
		this.subPhoto = subPhoto;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", price=" + price + ", categoryId=" + categoryId
				+ ", mainPhoto=" + mainPhoto + ", subPhoto=" + subPhoto + ", sales=" + sales + ", insertDate="
				+ insertDate + "]";
	}


	
	
	

}
