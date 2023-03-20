package com.mungyang.dto;

public class ReviewDTO {

	private int id;
	private String writerId;
	private int productId;
	private int star;
	private String title;
	private String content;
	private String photo;
	
	public ReviewDTO(String writerId, int productId, int star, String title, String content, String photo) {
		this.writerId = writerId;
		this.productId = productId;
		this.star = star;
		this.title = title;
		this.content = content;
		this.photo = photo;
	}
	
	public ReviewDTO(String writerId, int productId, int star, String title, String content) {
		this.writerId = writerId;
		this.productId = productId;
		this.star = star;
		this.title = title;
		this.content = content;
	}
	
	 public ReviewDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "ReviewDTO [id=" + id + ", writerId=" + writerId + ", productId=" + productId + ", star=" + star
				+ ", title=" + title + ", content=" + content + ", photo=" + photo + "]";
	}
	
}
